package com.toy.cosmos.api.service;

import com.toy.cosmos.api.model.request.BoardRequest;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@EntityScan(basePackages = {"com.toy.cosmos"})
@SpringBootTest
@ActiveProfiles("test")
public class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @Test
    @Transactional
    void insertBoardTest() {
        BoardRequest.Register request = new BoardRequest.Register();
        request.setTitle("새로운 제목6");
        request.setContent("새로운 내용6");
        Board board = boardRepository.getOne(6L);

        boardService.insertBoard(request);


        Assertions.assertEquals("새로운 제목6", board.getTitle());
    }

    @Test
    void getBoardsTest() {
        BoardRequest.Search request = new BoardRequest.Search();
        List<Board> boards = boardRepository.findAll();

        boardService.getBoards(request);

        Assertions.assertEquals(13, boards.size());
    }

    @Test
    @Transactional
    void getBoardTest() {
        Long boardId = 1L;

        boardService.getBoard(boardId);
    }

    @Test
    @Transactional
    void editBoardTest() {
        Long id = 1L;
        BoardRequest.Register request = new BoardRequest.Register();
        request.setTitle("변경된 제목1");
        request.setContent("변경된 내용1");

        boardService.editBoard(id,request);
    }

    @Test
    void deleteBoardTest() {
        Long id = 1L;

        boardService.deleteBoard(id);
    }
}
