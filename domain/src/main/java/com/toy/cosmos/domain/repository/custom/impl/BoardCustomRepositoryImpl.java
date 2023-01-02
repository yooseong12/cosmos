package com.toy.cosmos.domain.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.repository.custom.BoardCustomRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.toy.cosmos.domain.entity.QBoard.board;
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
}
