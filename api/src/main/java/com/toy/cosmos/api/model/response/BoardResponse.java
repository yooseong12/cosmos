package com.toy.cosmos.api.model.response;

import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@UtilityClass
public class BoardResponse { //todo: inner class 생성하기

    @SuperBuilder
    @Getter
    @Setter
    public static class GetOne extends GetMany {

        String content;

        Set<Comment> comment;

        public static GetOne of(Board board) {
            return GetOne.builder()
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

    @SuperBuilder
    @AllArgsConstructor
    @Getter
    @Setter
    public static class GetMany {

        Long id;

        String title;

        String writer;

        Integer hits;

        Integer liked;


        public static List<GetMany> of(List<Board> boards) {
            return boards.stream().map(GetMany::of).collect(Collectors.toList());
        }

        public static GetMany of(Board board) {
            return GetMany.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .hits(board.getHits())
                    .liked(board.getLiked())
                    .writer(board.getUser().getNickname())
                    .build();
        }
    }


}