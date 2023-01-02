package com.toy.cosmos.domain.repository;

import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.repository.custom.BoardCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {
    Optional<Board> findByTitle(String title);
}
