package com.toy.cosmos.api.model.response;

import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserResponse {

    @UtilityClass
    public static class Read {
        String email;

        String password;

        String nickname;

        String phone;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .password(password)
                    .nickname(nickname)
                    .phone(phone)
                    .build();
        }
    }

    @UtilityClass
    public static class Friend {

        Long friendId;

        String nickname;

        Status.UserFiend status;
    }

}
