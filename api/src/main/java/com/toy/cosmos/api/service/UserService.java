package com.toy.cosmos.api.service;

import com.toy.cosmos.api.exception.user.AlreadyExistUserException;
import com.toy.cosmos.api.exception.user.AlreadyExistUserFriendException;
import com.toy.cosmos.api.exception.user.NotFoundUserException;
import com.toy.cosmos.api.model.request.UserRequest;
import com.toy.cosmos.api.model.response.UserResponse;
import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.User;
import com.toy.cosmos.domain.entity.UserFriend;
import com.toy.cosmos.domain.repository.UserFriendRepository;
import com.toy.cosmos.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserFriendRepository userFriendRepository;

    public void join(UserRequest.Join request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new AlreadyExistUserException();
        });

        userRepository.save(request.toEntity());
    }

    public void requestFriend(Long id) {
        Long userId = getLoginUserId();

        userRepository.findById(id).orElseThrow(NotFoundUserException::new);
        userFriendRepository.findByUserAndFriendId(User.builder().id(userId).build(), id)
                .ifPresent(uf -> {
                    throw new AlreadyExistUserFriendException();
                });

        UserFriend userFriend = UserFriend.builder()
                .user(User.builder().id(userId).build())
                .friendId(id)
                .status(Status.UserFriend.FOLLOW)
                .build();

        userFriendRepository.save(userFriend);
    }

    public List<UserResponse.UserInfo> getFriends(UserRequest.Friend request) {
        Long userId = getLoginUserId();
        List<User> users = userRepository.findAllUserWithUserFriend(
                userId, request.getStatus()
        );

        return UserResponse.UserInfo.of(users);
    }

    public UserResponse.UserInfo findFriend(UserRequest.FindFriend request) {
        return UserResponse.UserInfo.of(
                userRepository.findBy(request.getEmail(), request.getPhone())
                        .orElseThrow(NotFoundUserException::new)
        );
    }

    // todo: spring security 작업 후 변경예정
    private Long getLoginUserId() {
        return 1L;
    }

    public void deleteFriend(Long userId, Long friendId) {
        userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        userRepository.deleteFriendIdByUserId(userId, friendId);
    }

    public void blockedFriend(Long userId, Long friendId) {
        userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        userRepository.blockedFriend(userId, friendId);
    }

    public void withdrawUser(Long id) {
        userRepository.findById(id).orElseThrow(NotFoundUserException::new);
        userRepository.withdrawByUserId(id);
    }

}
