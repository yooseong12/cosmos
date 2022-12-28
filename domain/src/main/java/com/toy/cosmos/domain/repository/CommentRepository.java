package com.toy.cosmos.domain.repository;

import com.toy.cosmos.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
