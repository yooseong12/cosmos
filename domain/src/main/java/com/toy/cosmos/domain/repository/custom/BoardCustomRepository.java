package com.toy.cosmos.domain.repository.custom;


import com.toy.cosmos.domain.entity.Board;

import java.util.List;

public interface BoardCustomRepository {

    List<Board> findBoardListByOrderByIdDesc(Integer page, Integer size);


    long updateHits(Long userId, Long boardId);
}
