package com.toy.cosmos.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.toy.cosmos.domain.common.CommonConstant;
import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.value.Authority;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity implements UserDetails {

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

    @NotNull
    @Enumerated(EnumType.STRING)
    Authority authority;

    @Enumerated(EnumType.STRING)
    Status.User status;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<UserFriend> userFriends;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Board> boards;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getAuthority().name()));
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isAdmin() {
        return this.getAuthority() == Authority.ROLE_ADMIN;
    }
}
