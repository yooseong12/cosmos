package com.toy.cosmos.domain.repository.custom;

import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.User;

public interface UserCustomRepository {

    User findUserWithUserFriend(Long id, Status.UserFiend status);

}
