package com.toy.cosmos.domain.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.User;
import com.toy.cosmos.domain.repository.custom.UserCustomRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

import static com.toy.cosmos.domain.entity.QUser.user;
import static com.toy.cosmos.domain.entity.QUserFriend.userFriend;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final EntityManager entityManager;

    @Override
    public User findUserWithUserFriend(Long id, Status.UserFiend status) {
        return new JPAQuery<User>(entityManager)
                .from(user)
                .join(user.userFriends, userFriend)
                .fetchJoin()
                .where(user.id.eq(id)
                        .and(userFriend.status.eq(status))
                )
                .fetchOne();
    }
}
