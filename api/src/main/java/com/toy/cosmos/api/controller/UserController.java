package com.toy.cosmos.api.controller;

import com.toy.cosmos.api.model.request.UserRequest;
import com.toy.cosmos.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public void getFriends(UserRequest.Friend request) {
        userService.getFriends(request);
    }
}
