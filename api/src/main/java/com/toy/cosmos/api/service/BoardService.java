package com.toy.cosmos.api.service;

import com.toy.cosmos.api.exception.user.NotFoundUserException;
import com.toy.cosmos.api.model.request.BoardRequest;
import com.toy.cosmos.api.model.response.BoardResponse;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.entity.User;
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
    public void insertBoard(BoardRequest.Register request, Long id) {
        User user = userRepository.findById(id).
                orElseThrow(NotFoundUserException::new);
        boardRepository.findByTitle(request.getTitle()).ifPresent(board -> {
            throw new IllegalArgumentException("이미 있는 제목"); // todo: exception 생성해야함
        });

        request.setWriter(user.getNickname());

        boardRepository.save(request.toEntity());
    }


    public BoardResponse.GetList getBoards() {
        List<Board> board = boardRepository.findAllByOrderByIdDesc();

        return null;
    }
}
