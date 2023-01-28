package com.toy.cosmos.api.model.request;

import com.toy.cosmos.domain.common.CommonConstant;
import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@UtilityClass
public class UserRequest {

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Login {

        @NotNull
        @Size(max = 50)
        @Pattern(regexp = CommonConstant.RegExp.EMAIL)
        String email;

        @NotNull
        @Size(max = 255)
        String password;

    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Join {

        @NotNull
        @Size(max = 50)
        @Pattern(regexp = CommonConstant.RegExp.EMAIL)
        String email;

        @NotNull
        @Size(max = 255)
        String password;

        @NotNull
        String nickname;

        @NotNull
        @Pattern(regexp = CommonConstant.RegExp.PHONE)
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


    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Friend {

        Status.UserFriend status;

    }

    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class FindFriend {
        String email;

        String phone;

    }

}
