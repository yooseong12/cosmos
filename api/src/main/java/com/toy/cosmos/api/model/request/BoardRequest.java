package com.toy.cosmos.api.model.request;

import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.entity.User;
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
    public static class Register { // todo: renaming

        @NotNull
        @Size(max = 50)
        String title;

        @NotNull
        String content;

        public Board toEntity(Long userId) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(User.builder().id(userId).build())
                    .build();
        }
    }


    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Search {

        Integer page = 0;

        Integer size = 5;

        /**
         * 몇개씩 가져올까?
         * 몇페이지인데?
         * @param userId
         * @return
         */
    }
}

