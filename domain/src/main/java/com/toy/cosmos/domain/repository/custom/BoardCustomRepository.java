package com.toy.cosmos.domain.repository.custom;


import com.toy.cosmos.domain.entity.Board;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface BoardCustomRepository {

    Optional<Board> findBoardWithUserBy(Long id);

    List<Board> findBoardListByOrderByIdDesc(Integer page, Integer size);

    long updateHits(Long id);

    long editBoard(@NotNull Long id,
                   @NotNull Long userId,
                   @NotNull String title,
                   @NotNull String content);

    long deleteBoard(Long id);

    long deleteComment(Long boardId, Long commentId);

    Optional<Board> findBoardWithCommentBy(Long id);
}
