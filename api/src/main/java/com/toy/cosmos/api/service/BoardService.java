package com.toy.cosmos.api.service;

import com.toy.cosmos.api.exception.board.NotFoundBoardException;
import com.toy.cosmos.api.exception.user.NotFoundUserException;
import com.toy.cosmos.api.model.request.BoardRequest;
import com.toy.cosmos.api.model.response.BoardResponse;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.repository.BoardRepository;
import com.toy.cosmos.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public BoardResponse getBoard(Long id) {
        Long userId = getLoginUserId();
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);

        if (!userId.equals(board.getUser().getId())) {
            boardRepository.updateHits(id);
        }

        return BoardResponse.of(board);
    }

    public void editBoard(Long id, BoardRequest.Register request) {
        Long userId = getLoginUserId();
        userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        Board board = boardRepository.findById(id).orElseThrow(NotFoundBoardException::new);
        // todo:


    }

    public void deleteBoard(Long id) {
        // todo:
    }

    private Long getLoginUserId() {
        return 2L;
    }
}
