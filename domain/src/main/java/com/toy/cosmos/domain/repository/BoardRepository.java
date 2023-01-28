package com.toy.cosmos.domain.repository;

import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.repository.custom.BoardCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {

    Optional<Board> findByIdAndStatus(Long id, Status.Board status);
    @Override
    default Optional<Board> findById(Long id) {
        return findByIdAndStatus(id, Status.Board.NORMAL);
    }
    
}
