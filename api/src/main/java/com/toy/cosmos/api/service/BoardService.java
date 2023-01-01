package com.toy.cosmos.api.service;

import com.toy.cosmos.api.model.request.BoardRequest;
import com.toy.cosmos.api.model.response.BoardResponse;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void insertBoard(BoardRequest.Register request) {
        Long userId = getLoginUserId();
        boardRepository.save(request.toEntity(userId));
    }

    public List<BoardResponse> getBoards(BoardRequest.Search request) {
        // todo: 유성 작업
        List<Board> boards = boardRepository.findAllByOrderByIdDesc();

        return BoardResponse.of(boards);
    }

    public BoardResponse getBoard(Long id) {
        Long userId = getLoginUserId();
        // todo: 유성 상세조회 작업
        Board board = boardRepository.findById(id).get();
        /**
         * 글 작성가 계속 조회를 하면 조회수 up 어뷰징 가능함.
         *
         * 조회수 count up
         * 글 작성자가 조회하는 경우라면, count up 하지 않는다.
         */

        return BoardResponse.of(board);
    }

    public void editBoard(Long id, BoardRequest.Register request) {
        // todo:
    }

    public void deleteBoard(Long id) {
        // todo:
    }

    private Long getLoginUserId() {
        return 1L;
    }
}
