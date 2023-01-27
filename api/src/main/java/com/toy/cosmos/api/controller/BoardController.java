package com.toy.cosmos.api.controller;

import com.toy.cosmos.api.model.request.BoardRequest;
import com.toy.cosmos.api.model.response.BoardResponse;
import com.toy.cosmos.api.model.response.Response;
import com.toy.cosmos.api.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    /**
     * 1. 등록(제목, 내용)
     * 2. 목록 조회(페이지네이션)
     * 3. 단건 조회(조회수 카운트, 좋아요, 댓글)
     * 4. 수정(제목, 내용)
     * 5. 삭제
     * <p>
     * Entity
     * 1. Board
     * 2. Comment
     */

    private final BoardService boardService;

    @PostMapping("/boards")
    public void insertBoard(@RequestBody BoardRequest.Register request) {
        boardService.insertBoard(request);
    }

    @GetMapping("/boards")
    public Response<List<BoardResponse.GetMany>> getBoards(BoardRequest.Search request) {
        return Response.<List<BoardResponse.GetMany>>builder()
                .code(HttpStatus.OK.value())
                .data(boardService.getBoards(request))
                .build();
    }

    @GetMapping("/boards/{id:[\\d]+}")
    public Response<BoardResponse.GetOne> getBoard(@PathVariable Long id) {
        return Response.<BoardResponse.GetOne>builder()
                .code(HttpStatus.OK.value())
                .data(boardService.getBoard(id))
                .build();
    }

    @PatchMapping("/boards/{id:[\\d]+}")
    public Response<Void> editBoard(@PathVariable Long id, BoardRequest.Register request) {
        boardService.editBoard(id, request);
        return Response.<Void>builder().build();
    }

    @DeleteMapping("/boards/{id:[\\d]+}")
    public Response<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return Response.<Void>builder().build();
    }

    @PostMapping("/comment/{id:[\\d]+}")
    public Response<Void> createComment(@PathVariable Long id, BoardRequest.Comments request) {
        boardService.createComment(id, request);
        return Response.<Void>builder().build();
    }

    @PostMapping("/comment/status/{boardId:[\\d]+}/{commentId:[\\d]+}")
    public Response<Void> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        boardService.deleteComment(boardId, commentId);
        return Response.<Void>builder().build();
    }

}
