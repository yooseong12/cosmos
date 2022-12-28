package com.toy.cosmos.api.model.response;

import com.toy.cosmos.domain.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BoardResponse {

    @UtilityClass
    @Builder
    @AllArgsConstructor
    @Getter
    @Setter
    public static class GetList {

        Long id;

        String title;

        Integer hits;

        Integer like;

        public Board toEntity() {
            return Board.builder()
                    .id(id)
                    .title(title)
                    .hits(hits)
                    .like(like)
                    .build();
        }
    }
}

