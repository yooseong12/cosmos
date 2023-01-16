package com.toy.cosmos.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.entity.Comment;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BoardResponse {

    Long id;

    String title;

    String content;

    String writer;

    Integer hits;

    Integer liked;

    Set<Comment> comment;

    public static List<BoardResponse> ofs(List<Board> boards) {
        return boards.stream().map(BoardResponse::ofs).collect(Collectors.toList());
    }

    public static BoardResponse ofs(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .hits(board.getHits())
                .liked(board.getLiked())
                .writer(board.getUser().getNickname())
                .build();
    }

    public static BoardResponse of(Board board) {
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .hits(board.getHits())
                .liked(board.getLiked())
                .writer(board.getUser().getNickname())
                .comment(board.getComments())
                .build();
    }

}