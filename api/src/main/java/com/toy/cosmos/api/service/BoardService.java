package com.toy.cosmos.api.service;

import com.toy.cosmos.api.exception.board.AccessDeniedException;
import com.toy.cosmos.api.exception.board.NotFoundBoardException;
import com.toy.cosmos.api.model.request.BoardRequest;
import com.toy.cosmos.api.model.response.BoardResponse;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<Board> boards = boardRepository.findBoardListByOrderByIdDesc(request.getPage(), request.getSize());

        return BoardResponse.of(boards);
    }

    public BoardResponse getBoard(Long id) {
        Long userId = getLoginUserId();
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);

        if (!userId.equals(board.getUser().getId())) {
            boardRepository.updateHits(id);
        }

        return BoardResponse.of(board);
    }

    @Transactional
    public void editBoard(Long id, BoardRequest.Register request) {
        Long userId = getLoginUserId();
        Board board = boardRepository.findBoardWithUserBy(id).orElseThrow(NotFoundBoardException::new);

        if (!userId.equals(board.getUser().getId())) {
            throw new AccessDeniedException();
        }

        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Long userId = getLoginUserId();
        Board board = boardRepository.findBoardWithUserBy(id).orElseThrow(NotFoundBoardException::new);

        if (!userId.equals(board.getUser().getId())) {
            throw new AccessDeniedException();
        }

        boardRepository.deleteBoard(id);
    }

    private Long getLoginUserId() {
        return 1L;
    }
}
