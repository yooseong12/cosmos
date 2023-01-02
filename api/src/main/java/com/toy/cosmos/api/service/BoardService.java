package com.toy.cosmos.api.service;

import com.toy.cosmos.api.exception.board.NotFoundBoardException;
import com.toy.cosmos.api.exception.user.NotFoundUserException;
import com.toy.cosmos.api.model.request.BoardRequest;
import com.toy.cosmos.api.model.response.BoardResponse;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.entity.User;
import com.toy.cosmos.domain.repository.BoardRepository;
import com.toy.cosmos.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    @Transactional
    public void insertBoard(BoardRequest.Register request) {
        Long userId = getLoginUserId();
        boardRepository.save(request.toEntity(userId));
    }

    public List<BoardResponse> getBoards(BoardRequest.Search request) {
        List<Board> boards = boardRepository.findBoardListByOrderByIdDesc(request.getPage(), request.getSize());

        return BoardResponse.of(boards);
    }

    public BoardResponse getBoard(Long boardId) {
        Long userId = getLoginUserId();
        // todo: 유성 상세조회 작업

        userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        /**
         * 글 작성가 계속 조회를 하면 조회수 up 어뷰징 가능함.
         *
         * 조회수 count up
         * 글 작성자가 조회하는 경우라면, count up 하지 않는다.
         */
        boardRepository.updateHits(userId, boardId);


        return BoardResponse.of(board);
    }

    public void editBoard(Long boardId, BoardRequest.Register request) {
        Long userId = getLoginUserId();
        userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
    }

    public void deleteBoard(Long id) {
        // todo:
    }

    private Long getLoginUserId() {
        return 2L;
    }
}
