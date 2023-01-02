package com.toy.cosmos.domain.repository.custom.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.repository.custom.BoardCustomRepository;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.toy.cosmos.domain.entity.QBoard.board;
import static com.toy.cosmos.domain.entity.QUser.user;
import static com.toy.cosmos.domain.entity.QUserFriend.userFriend;

@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository {

    private final EntityManager entityManager;


    @Override
    public List<Board> findBoardListByOrderByIdDesc(Integer page, Integer size) {
        return new JPAQuery<Board>(entityManager)
                .from(board)
                .limit(size).offset(page)
                .fetch();
    }

    @Override
    public long updateHits(Long userId, Long boardId) {
        return new JPAUpdateClause(entityManager, board)
                .set(board.hits, board.hits.add(1))
                .where(board.id.eq(boardId)
                        .and(board.user.id.ne(userId)))
                .execute();
    }
}
