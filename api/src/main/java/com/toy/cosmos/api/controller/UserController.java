package com.toy.cosmos.api.controller;

import com.toy.cosmos.api.model.request.UserRequest;
import com.toy.cosmos.api.model.response.Response;
import com.toy.cosmos.api.model.response.UserResponse;
import com.toy.cosmos.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public void join(UserRequest.Join request) {
        userService.join(request);
    }

    @PostMapping("/friend/{id:[\\d]+}")
    public void requestFriend(@PathVariable Long id) {
        userService.requestFriend(id);
    }

    @GetMapping("/friends")
    public Response<List<UserResponse.UserInfo>> getFriends(UserRequest.Friend request) {
        return Response.<List<UserResponse.UserInfo>>builder()
                .code(HttpStatus.OK.value())
                .data(userService.getFriends(request))
                .build();
    }

    // 이메일, 핸드폰번호로 친구를 검색하고싶다.
    @GetMapping("/friend")
    public Response<UserResponse.UserInfo> findFriend(UserRequest.FindFriend request) {
        if (ObjectUtils.isEmpty(request.getEmail()) && ObjectUtils.isEmpty(request.getPhone())) {
            throw new ValidationException();
        }

        return Response.<UserResponse.UserInfo>builder()
                .code(HttpStatus.OK.value())
                .data(userService.findFriend(request))
                .build();
    }

    @DeleteMapping("/friend/{friendId:[\\d]+}")
    public Response<Void> deleteFriend(@PathVariable Long friendId) {
        return userService.deleteFriend(friendId);
    }

    @PatchMapping("/friend/{friendId:[\\d]+}")
    public Response<Void> blockedFriend(@PathVariable Long friendId) {
        // todo
        return null;
    }

}
