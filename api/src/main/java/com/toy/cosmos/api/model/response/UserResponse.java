package com.toy.cosmos.api.model.response;

import com.toy.cosmos.domain.entity.User;
import com.toy.cosmos.domain.entity.UserFriend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class UserResponse {

    @Builder
    @AllArgsConstructor
    @Getter
    @Setter
    public static class UserInfo {

        Long id;

        String nickname;

        String phone;

        String email;

        public static List<UserInfo> of(List<User> users) {
            return users.stream()
                    .map(UserInfo::of)
                    .collect(Collectors.toList());
        }

        public static UserInfo of(User user) {
            return UserInfo.builder()
                    .id(user.getId())
                    .nickname(user.getNickname())
                    .phone(user.getPhone())
                    .email(user.getEmail())
                    .build();
        }

    }

}
