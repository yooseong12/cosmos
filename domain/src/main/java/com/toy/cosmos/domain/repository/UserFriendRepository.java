package com.toy.cosmos.domain.repository;

import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.User;
import com.toy.cosmos.domain.entity.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserFriendRepository extends JpaRepository<UserFriend, Long> {

    List<UserFriend> findAllByUserAndStatus(@Param("user") @NotEmpty User user,
                                            @Param("status") @NotNull Status.UserFriend status);

}
