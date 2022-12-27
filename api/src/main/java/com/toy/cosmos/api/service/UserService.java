package com.toy.cosmos.api.service;

import com.toy.cosmos.api.exception.user.AlreadyExistUserException;
import com.toy.cosmos.api.exception.user.NotFoundUserException;
import com.toy.cosmos.api.model.request.UserRequest;
import com.toy.cosmos.api.model.response.Response;
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
        User friend = userRepository.findById(id).orElseThrow(NotFoundUserException::new);

        UserFriend userFriend = com.toy.cosmos.domain.entity.UserFriend.builder()
                .user(User.builder().id(userId).build())
                .friendId(friend.getId())
                .status(Status.UserFriend.FOLLOW)
                .build();

        // todo: 중복 발생시 DataIntegrityViolationException 발생, 처리 예정
        userFriendRepository.save(userFriend);
    }

    public UserResponse.Friend getFriend(String email) {
        Long userId = getLoginUserId();
        UserFriend userFriend = userFriendRepository.findById(1L).get(); // todo: Request 파라미터

        return UserResponse.Friend.of(userFriend);
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


    public Response<Void> deleteFriend(Long friendId) {
        return null;
    }
}
