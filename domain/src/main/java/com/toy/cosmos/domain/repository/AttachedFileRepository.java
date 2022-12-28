package com.toy.cosmos.domain.repository;

import com.toy.cosmos.domain.entity.AttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachedFileRepository extends JpaRepository<AttachedFile, Long> {
}
