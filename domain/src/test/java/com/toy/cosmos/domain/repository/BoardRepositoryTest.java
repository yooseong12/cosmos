package com.toy.cosmos.domain.repository;

import com.toy.cosmos.domain.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
    void findBoardWithUserByTest() {
        Long id = 1L;
        Board board = boardRepository.findBoardWithUserBy(id).orElseThrow();

        log.info("board: {}", board);
    }


}
