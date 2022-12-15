package com.toy.cosmos.api.service;

import com.toy.cosmos.api.model.request.UserRequest;
import com.toy.cosmos.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@EntityScan(basePackages = {"com.toy.cosmos"})
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @Rollback(value = false)
    void joinTest() {
        // given
        UserRequest.Join request = new UserRequest.Join();
        request.setEmail("c@naver.com");
        request.setPassword("1234");
        request.setNickname("아무개");
        request.setPhone("0101234564");

        // when
        userService.join(request);
        System.out.println(request.getEmail());

        // then
    }
}