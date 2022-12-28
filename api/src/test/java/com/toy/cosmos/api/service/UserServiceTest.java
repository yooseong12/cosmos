package com.toy.cosmos.api.service;

import com.toy.cosmos.api.model.request.UserRequest;
import com.toy.cosmos.api.model.response.UserResponse;
import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.User;
import com.toy.cosmos.domain.entity.UserFriend;
import com.toy.cosmos.domain.repository.UserFriendRepository;
import com.toy.cosmos.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Slf4j
@EntityScan(basePackages = {"com.toy.cosmos"})
@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserFriendRepository userFriendRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

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
        // todo: 좀더 알아보기
    void findUserWithUserFriendsTest() {
        Long id = 1L;

        User 김유성 = userRepository.getOne(id);
        Set<UserFriend> 유성이친구들 = 김유성.getUserFriends();

        Assertions.assertEquals(2, 유성이친구들.size());
    }

    @Test
    @Transactional
    void getFriendsTest() {
        UserRequest.Friend request = new UserRequest.Friend();
        request.setStatus(Status.UserFriend.FOLLOW);

        List<UserResponse.UserInfo> friends = userService.getFriends(request);

        Assertions.assertEquals(2, friends.size());
        Assertions.assertEquals(2, friends.stream().findFirst().orElseThrow().getId());
    }


    @Test
    void findFriendTest() {
        String email = "a@naver.com";
        String phone = "01012345678";

        UserRequest.FindFriend emailRequest = new UserRequest.FindFriend();
        emailRequest.setEmail(email);

        UserRequest.FindFriend phoneRequest = new UserRequest.FindFriend();
        phoneRequest.setPhone(phone);

        UserResponse.UserInfo findByEmail = userService.findFriend(emailRequest);

        UserResponse.UserInfo findByPhone = userService.findFriend(phoneRequest);

        Assertions.assertEquals(1, findByEmail.getId());
        Assertions.assertEquals(2, findByPhone.getId());
    }

    @Test
    @Transactional
    void deleteFriendTest() {
        Long userId = 1l;
        Long friendId = 2L;

        User user = userRepository.getOne(userId);
        Set<UserFriend> userFriends = user.getUserFriends();

        userService.deleteFriend(userId, friendId);

        Assertions.assertEquals(1, userFriends.size());
    }

    @Test
    @Transactional
    void blockedFriendTest() {
        Long userId = 1L;
        Long friendId = 2L;
        UserFriend userFriend = userFriendRepository.findById(userId).get();

        userService.blockedFriend(userId, friendId);


        Assertions.assertEquals("BLOCKED", userFriend.getStatus());
    }


}