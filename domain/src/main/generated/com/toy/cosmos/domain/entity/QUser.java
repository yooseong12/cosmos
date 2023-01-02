package com.toy.cosmos.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1772003571L;

    public static final QUser user = new QUser("user");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final EnumPath<com.toy.cosmos.domain.value.Authority> authority = createEnum("authority", com.toy.cosmos.domain.value.Authority.class);

    public final ListPath<Board, QBoard> boards = this.<Board, QBoard>createList("boards", Board.class, QBoard.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt = _super.updatedAt;

    public final SetPath<UserFriend, QUserFriend> userFriends = this.<UserFriend, QUserFriend>createSet("userFriends", UserFriend.class, QUserFriend.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

