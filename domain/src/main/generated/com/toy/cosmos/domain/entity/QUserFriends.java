package com.toy.cosmos.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserFriends is a Querydsl query type for UserFriends
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserFriends extends EntityPathBase<UserFriends> {

    private static final long serialVersionUID = -1092691678L;

    public static final QUserFriends userFriends = new QUserFriends("userFriends");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> friendId = createNumber("friendId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.toy.cosmos.domain.common.Status.UserFiends> status = createEnum("status", com.toy.cosmos.domain.common.Status.UserFiends.class);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserFriends(String variable) {
        super(UserFriends.class, forVariable(variable));
    }

    public QUserFriends(Path<? extends UserFriends> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserFriends(PathMetadata metadata) {
        super(UserFriends.class, metadata);
    }

}

