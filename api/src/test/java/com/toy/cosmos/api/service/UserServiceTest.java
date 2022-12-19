package com.toy.cosmos.api.service;

import com.toy.cosmos.api.model.request.UserRequest;
import com.toy.cosmos.domain.entity.User;
import com.toy.cosmos.domain.entity.UserFriend;
import com.toy.cosmos.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Set;

@Slf4j
@EntityScan(basePackages = {"com.toy.cosmos"})
@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void joinTest() {
        // given
        UserRequest.Join request = new UserRequest.Join();
        request.setEmail("d@naver.com");
        request.setPassword("1234");
        request.setNickname("아무개");
        request.setPhone("0101234564");

        // when
        userService.join(request);

        // then

    }

    @Test
    @Transactional
    void findUserWithUserFriendsTest() {
        Long id = 1L;

        User 김유성 = userRepository.getOne(id);
        Set<UserFriend> 유성이친구들 = 김유성.getUserFriends();

        Assertions.assertEquals(2, 유성이친구들.size());
    }


}