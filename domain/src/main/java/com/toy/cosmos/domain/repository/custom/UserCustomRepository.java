package com.toy.cosmos.domain.repository.custom;

import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserCustomRepository {

    List<User> findAllUserWithUserFriend(Long id, Status.UserFriend status);

    Optional<User> findBy(String email, String phone);

    long deleteFriendIdByUserId(Long userId, Long friendId);

    long blockedFriend(Long userId, Long friendId);

    long withdrawByUserId(Long id);

}
