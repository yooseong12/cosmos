package com.toy.cosmos.domain.repository.custom.impl;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.User;
import com.toy.cosmos.domain.repository.custom.UserCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.toy.cosmos.domain.entity.QUser.user;
import static com.toy.cosmos.domain.entity.QUserFriend.userFriend;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllUserWithUserFriend(Long id, Status.UserFriend status) {
        return new JPAQuery<User>(entityManager)
                .from(user)
                .join(userFriend).on(user.id.eq(userFriend.friendId))
                .where(userFriend.user.id.eq(id)
                        .and(userFriend.status.eq(status))
                )
                .fetch();
    }

    @Override
    public Optional<User> findBy(String email, String phone) {
        return Optional.ofNullable(new JPAQuery<User>(entityManager)
                .from(user)
                .where(ObjectUtils.isEmpty(email) ? user.phone.eq(phone) : user.email.eq(email))
                .fetchOne()
        );
    }

    @Override
    public long deleteFriendIdByUserId(Long userId, Long friendId) {
        return new JPADeleteClause(entityManager, userFriend)
                .where(user.id.eq(userId)
                        .and(userFriend.friendId.eq(friendId)))
                .execute();

    }

    @Override
    public long blockedFriend(Long userId, Long friendId) {
        return new JPAUpdateClause(entityManager, userFriend)
                .set(userFriend.status, Status.UserFriend.BLOCKED)
                .where(user.id.eq(userId)
                        .and(userFriend.friendId.eq(friendId))
                        .and(userFriend.status.eq(Status.UserFriend.FOLLOW)))
                .execute();
    }

    @Override
    public long withdrawByUserId(Long id) {
        return new JPAUpdateClause(entityManager, user)
                .set(user.status, Status.User.DELETE)
                .where(user.id.eq(id).and(user.status.eq(Status.User.NORMAL)))
                .execute();
    }
}
