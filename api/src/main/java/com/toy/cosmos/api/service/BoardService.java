package com.toy.cosmos.api.service;

import com.toy.cosmos.api.exception.board.AccessDeniedException;
import com.toy.cosmos.api.exception.board.NotFoundBoardException;
import com.toy.cosmos.api.model.request.BoardRequest;
import com.toy.cosmos.api.model.response.BoardResponse;
import com.toy.cosmos.auth.service.LoginService;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.repository.BoardRepository;
import com.toy.cosmos.domain.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final LoginService loginService;

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public void insertBoard(BoardRequest.Register request) {
        Long userId = loginService.getLoginUserId();
        boardRepository.save(request.toEntity(userId));
    }

    public List<BoardResponse.Search> getBoards(BoardRequest.Search request) {
        List<Board> boards = boardRepository.findBoardListByOrderByIdDesc(request.getPage(), request.getSize());

        return BoardResponse.Search.of(boards);
    }

    @Transactional
    public BoardResponse.Detail getBoard(Long id) {
        Long userId = loginService.getLoginUserId();
        Board board = boardRepository.findBoardWithCommentBy(id).orElseThrow(NotFoundBoardException::new);

        if (!userId.equals(board.getUser().getId())) {
            boardRepository.updateHits(id);
        }

        return BoardResponse.Detail.of(board);
    }

    @Transactional
    public void editBoard(Long id, BoardRequest.Register request) {
        Long userId = loginService.getLoginUserId();
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
        Long userId = loginService.getLoginUserId();
        Board board = boardRepository.findBoardWithUserBy(id).orElseThrow(NotFoundBoardException::new);

        if (!userId.equals(board.getUser().getId())) {
            throw new AccessDeniedException();
        }

        boardRepository.deleteBoard(id);
    }

    @Transactional
    public void createComment(Long boardId, BoardRequest.Comments request) {
        boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        commentRepository.save(request.toEntity(boardId));
    }

    @Transactional
    public void deleteComment(Long boardId, Long commentId) {
        Long userId = loginService.getLoginUserId();
        Board board = boardRepository.findBoardWithUserBy(boardId).orElseThrow(NotFoundBoardException::new);

        if (!userId.equals(board.getUser().getId())) {
            throw new AccessDeniedException();
        }

        boardRepository.deleteComment(boardId, commentId);
    }

}
