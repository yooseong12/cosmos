package com.toy.cosmos.domain.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.repository.custom.BoardCustomRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.toy.cosmos.domain.entity.QBoard.board;
import static com.toy.cosmos.domain.entity.QComment.comment;
import static com.toy.cosmos.domain.entity.QUser.user;

@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository {

    private final EntityManager entityManager;


    @Override
    public Optional<Board> findBoardWithUserBy(Long id) {
        return Optional.ofNullable(new JPAQuery<Board>(entityManager)
                .from(board)
                .join(board.user, user)
                .fetchJoin()
                .where(board.id.eq(id))
                .fetchOne());
    }

    @Override
    public List<Board> findBoardListByOrderByIdDesc(Integer page, Integer size) {
        return new JPAQuery<Board>(entityManager)
                .from(board)
                .where(board.status.eq(Status.Board.NORMAL))
                .limit(size).offset(page)
                .fetch();
    }


    @Override
    public long updateHits(Long id) {
        return new JPAUpdateClause(entityManager, board)
                .set(board.hits, board.hits.add(1))
                .where(board.id.eq(id))
                .execute();
    }

    @Override
    public long editBoard(Long id, Long userId, String title, String content) {
        return new JPAUpdateClause(entityManager, board)
                .set(board.title, board.title.append(title))
                .set(board.content, board.content.append(content))
                .where(
                        board.id.eq(id),
                        board.user.id.eq(userId)
                )
                .execute();
    }

    @Override
    public long deleteBoard(Long id) {
        return new JPAUpdateClause(entityManager, board)
                .set(board.status, Status.Board.DELETE)
                .where(board.id.eq(id).and(board.status.eq(Status.Board.NORMAL)))
                .execute();
    }

    @Override
    public long deleteComment(Long boardId, Long commentId) {
        return new JPAUpdateClause(entityManager, comment)
                .set(comment.status, Status.Comment.DELETE)
                .where(comment.id.eq(commentId)
                        .and(comment.board.id.eq(boardId))
                        .and(comment.status.eq(Status.Comment.NORMAL)))
                .execute();
    }

    @Override
    public Optional<Board> findBoardWithCommentBy(Long id) {
        return Optional.ofNullable(new JPAQuery<Board>(entityManager)
                .from(board)
                .join(board.comments, comment)
                .fetchJoin()
                .where(board.id.eq(id)
                        .and(board.status.eq(Status.Board.NORMAL))
                        .and(comment.status.eq(Status.Comment.NORMAL)))
                .fetchOne());
    }

}
