package com.toy.cosmos.api.controller;

import com.toy.cosmos.api.model.request.UserRequest;
import com.toy.cosmos.api.service.UserService;
import com.toy.cosmos.domain.entity.UserFriend;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public List<UserFriend> getFriends(UserRequest.Friend request) {
        return userService.getFriends(request);
    }
}
