package com.toy.cosmos.domain.repository;

import com.toy.cosmos.domain.entity.User;
import com.toy.cosmos.domain.repository.custom.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    Optional<User> findByEmail(@NotNull String email);

    List<User> findAllById(@NotNull Long id);
}
