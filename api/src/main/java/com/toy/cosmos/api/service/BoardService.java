package com.toy.cosmos.api.service;

import com.toy.cosmos.api.exception.board.NotFoundBoardException;
import com.toy.cosmos.api.model.request.BoardRequest;
import com.toy.cosmos.api.model.response.BoardResponse;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.repository.BoardRepository;
import com.toy.cosmos.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void editBoard(Long id, BoardRequest.Register request) {
        Long userId = getLoginUserId();
        Board board = boardRepository.findBoardWithUserBy(id).orElseThrow(NotFoundBoardException::new);

        if (!userId.equals(board.getUser().getId())) {
            throw new AccessDeniedException("권한이 없습니다.");
            // 문제 response가 뭐가 나올까?
            /**
             *
             * 왜 code가 500이 나오고, message가 "권한이 없습니다." 나온다고 생각했어?
             * {
             *      code: 500,
             *      message: "권한이 없습니다."
             * }
             */
            /**
             * todo:
             * 문제 1)
             * response
             * {
             *      code: 9003,
             *      message: "권한이 없습니다."
             * }
             */
        }

        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long id) { // todo: 삭제 시 status 변경
        Long userId = getLoginUserId();
        Board board = boardRepository.findBoardWithUserBy(id).orElseThrow(NotFoundBoardException::new);

        if (!userId.equals(board.getUser().getId())) {
            throw new AccessDeniedException("권한이 없습니다.");
        }

        boardRepository.delete(board);
    }

    private Long getLoginUserId() {
        return 1L;
    }
}
