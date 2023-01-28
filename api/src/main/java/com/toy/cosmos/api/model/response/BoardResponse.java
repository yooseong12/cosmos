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
// todo 유성:
public class BoardResponse {

    @Getter
    @Setter
    @SuperBuilder
    public static class GetOne extends GetMany {

        String content;

        Set<Comment> comments;

        public static GetOne of(Board board) {
            return GetOne.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .hits(board.getHits())
                    .liked(board.getLiked())
                    .writer(board.getUser().getNickname())
                    .comments(board.getComments())
                    .build();
        }
    }

    @Getter
    @Setter
    @SuperBuilder
    @AllArgsConstructor
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
    }

    @Getter
    @Setter
    @SuperBuilder
    public static class Search extends BoardBase {

        public Search(Long id, String title, String writer, Integer hits, Integer liked) {
            super(id, title, writer, hits, liked);
        }

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