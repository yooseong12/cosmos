package com.toy.cosmos.domain.repository;

import com.toy.cosmos.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByTitle(String title);

    List<Board> findAllByOrderByIdDesc();
}
