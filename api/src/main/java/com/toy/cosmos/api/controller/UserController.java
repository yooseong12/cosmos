package com.toy.cosmos.api.controller;

import com.toy.cosmos.api.model.request.UserRequest;
import com.toy.cosmos.api.model.response.Response;
import com.toy.cosmos.api.model.response.UserResponse;
import com.toy.cosmos.api.service.UserService;
import com.toy.cosmos.auth.model.TokenResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody UserRequest.Login request) {
        return userService.login(request);
    }

    @PostMapping("/join")
    public void join(UserRequest.Join request) {
        userService.join(request);
    }

    @PostMapping("/withdraw/{id:[\\d]+}")
    @PreAuthorize("isAuthenticated()")
    public void withdrawUser(@PathVariable Long id) {
        userService.withdrawUser(id);
    }

    @PostMapping("/friend/{id:[\\d]+}")
    @PreAuthorize("isAuthenticated()")
    public void requestFriend(@PathVariable Long id) {
        userService.requestFriend(id);
    }

    @GetMapping("/friends")
    @PreAuthorize("isAuthenticated()")
    public Response<List<UserResponse.UserInfo>> getFriends(UserRequest.Friend request) {
        return Response.<List<UserResponse.UserInfo>>builder()
                .code(HttpStatus.OK.value())
                .data(userService.getFriends(request))
                .build();
    }

    @GetMapping("/friend")
    @PreAuthorize("isAuthenticated()")
    public Response<UserResponse.UserInfo> findFriend(UserRequest.FindFriend request) {
        if (ObjectUtils.isEmpty(request.getEmail()) && ObjectUtils.isEmpty(request.getPhone())) {
            throw new ValidationException();
        }

        return Response.<UserResponse.UserInfo>builder()
                .data(userService.findFriend(request))
                .build();
    }

    @DeleteMapping("/friend/{userId:[\\d]+}/{friendId:[\\d]+}")
    @PreAuthorize("isAuthenticated()")
    public Response<Void> deleteFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        userService.deleteFriend(userId, friendId);
        return Response.<Void>builder()
                .build();
    }

    @PatchMapping("/friend/{userId:[\\d]+}/{friendId:[\\d]+}")
    @PreAuthorize("isAuthenticated()")
    public Response<Void> blockedFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        userService.blockedFriend(userId, friendId);
        return Response.<Void>builder()
                .build();
    }

}
