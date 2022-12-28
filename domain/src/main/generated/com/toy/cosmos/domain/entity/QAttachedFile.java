package com.toy.cosmos.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttachedFile is a Querydsl query type for AttachedFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAttachedFile extends EntityPathBase<AttachedFile> {

    private static final long serialVersionUID = 1012040296L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttachedFile attachedFile = new QAttachedFile("attachedFile");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBoard board;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> updatedAt = _super.updatedAt;

    public QAttachedFile(String variable) {
        this(AttachedFile.class, forVariable(variable), INITS);
    }

    public QAttachedFile(Path<? extends AttachedFile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttachedFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttachedFile(PathMetadata metadata, PathInits inits) {
        this(AttachedFile.class, metadata, inits);
    }

    public QAttachedFile(Class<? extends AttachedFile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

