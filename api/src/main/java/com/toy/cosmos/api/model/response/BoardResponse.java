package com.toy.cosmos.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.toy.cosmos.domain.entity.Board;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
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

    Integer hits;

    Integer like;

    public static List<BoardResponse> of(List<Board> boards) {
        return boards.stream().map(BoardResponse::of).collect(Collectors.toList());
    }

    public static BoardResponse of(Board boards) {
        return null;
    }

}

