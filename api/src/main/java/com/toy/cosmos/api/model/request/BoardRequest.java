package com.toy.cosmos.api.model.request;

import com.toy.cosmos.domain.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@UtilityClass
public class BoardRequest {

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Register {

        @NotNull
        @Size(max = 50)
        String title;

        @NotNull
        String content;

        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }
}
