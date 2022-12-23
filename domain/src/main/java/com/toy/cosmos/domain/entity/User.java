package com.toy.cosmos.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.toy.cosmos.domain.common.CommonConstant;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull @Size(max = 50)
    @Column(unique = true)
    @Pattern(regexp = CommonConstant.RegExp.EMAIL)
    String email;

    @NotNull @Size(max = 255)
    String password;

    @NotNull
    String nickname;

    @NotNull @Pattern(regexp = CommonConstant.RegExp.PHONE)
    String phone;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<UserFriend> userFriends;

}
