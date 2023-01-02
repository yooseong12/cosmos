package com.toy.cosmos.domain.repository.custom;


import com.toy.cosmos.domain.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardCustomRepository {

    Optional<Board> findBoardWithUserBy(Long id);

    List<Board> findBoardListByOrderByIdDesc(Integer page, Integer size);


    long updateHits(Long id);
}
