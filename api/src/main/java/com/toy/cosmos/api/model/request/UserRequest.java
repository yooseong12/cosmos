package com.toy.cosmos.api.model.request;

import com.toy.cosmos.domain.common.CommonConstant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {

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
    }
}
