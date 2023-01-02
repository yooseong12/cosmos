package com.toy.cosmos.domain.repository;

import com.toy.cosmos.domain.entity.Board;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("domain-test")
public class BoardRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void findBoardWithUserByTest() {
        Long id = 1L;
        Board board = boardRepository.findBoardWithUserBy(id).orElseThrow();

        log.info("board: {}", board);
    }


    @Test
    @Transactional
    public void editBoardTest() {
        Long id = 1L;
        String title = "변경된 제목1";
        String content = "변경된 내용1";
        Board board = boardRepository.findById(id).get();

        boardRepository.editBoard(title, content, id);

        Assertions.assertEquals("변경된 제목1", board.getTitle());
    }
}