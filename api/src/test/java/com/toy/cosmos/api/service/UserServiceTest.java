package com.toy.cosmos.api.service;

import com.toy.cosmos.api.model.request.UserRequest;
import com.toy.cosmos.domain.entity.User;
import com.toy.cosmos.domain.repository.UserRepository;
import org.junit.Assert;
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

    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(value = false)
    void joinTest() {
        // given
        UserRequest.Join request = new UserRequest.Join();
        Long userId = 1L;

        User user = userRepository.findById(userId).get();

        request.setEmail("d@naver.com");
        request.setPassword("1234");
        request.setNickname("아무개");
        request.setPhone("0101234564");

        // when
        userService.join(request);

        // then
        Assert.assertEquals("d@naver.com", user.getEmail());
    }

//    @Test
//    @Rollback(value = false)
//    void readTest() {
//        //given
//        Long userId = 1L;
//
//        //when
//        userService.read(userId);
//        //then
//    }
}