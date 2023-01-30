package com.toy.cosmos.api.model.response;

import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@UtilityClass
public class BoardResponse {

    @Getter
    @Setter
    @SuperBuilder
    @AllArgsConstructor
    public static class BoardBase {

        Long id;

        String title;

        String writer;

        Integer hits;

        Integer liked;

    }

    @Getter
    @Setter
    @SuperBuilder
    public static class Detail extends BoardBase {

        String content;

        Set<Comment> comments;

        public static Detail of(Board board) {
            return Detail.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .hits(board.getHits())
                    .liked(board.getLiked())
                    .writer(board.getUser().getNickname())
                    .content(board.getContent())
                    .comments(board.getComments())
                    .build();
        }

    }

    @Getter
    @Setter
    @SuperBuilder
    public static class Search extends BoardBase {

        public static List<Search> of(List<Board> boards) {
            return boards.stream().map(Search::of).collect(Collectors.toList());
        }

        public static Search of(Board board) {
            return Search.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .hits(board.getHits())
                    .liked(board.getLiked())
                    .writer(board.getUser().getNickname())
                    .build();
        }

    }

}