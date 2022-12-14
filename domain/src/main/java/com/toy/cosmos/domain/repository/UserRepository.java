package com.toy.cosmos.domain.repository;

import com.toy.cosmos.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
