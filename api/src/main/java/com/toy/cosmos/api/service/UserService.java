package com.toy.cosmos.api.service;

import com.toy.cosmos.api.exception.user.AlreadyExistUserException;
import com.toy.cosmos.api.exception.user.NotFoundUserException;
import com.toy.cosmos.api.model.request.UserRequest;
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
        User friend = userRepository.findById(id).orElseThrow(NotFoundUserException::new);

        UserFriend userFriend = com.toy.cosmos.domain.entity.UserFriend.builder()
                .user(User.builder().id(userId).build())
                .friendId(friend.getId())
                .status(Status.UserFriend.FOLLOW)
                .build();

        // todo: 중복 발생시 DataIntegrityViolationException 발생, 처리 예정
        userFriendRepository.save(userFriend);
    }

    public List<UserFriend> getFriends(UserRequest.Friend request) {
        Long userId = getLoginUserId();
        request.setStatus(Status.UserFriend.FOLLOW);
        return userFriendRepository.findAllByUserAndStatus(
                User.builder().id(userId).build(), request.getStatus()
        );


    }

    // todo: spring security 작업 후 변경예정
    private Long getLoginUserId() {
        return 1L;
    }


}
