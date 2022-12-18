package com.toy.cosmos.api.service;

import com.toy.cosmos.api.model.request.UserRequest;
import com.toy.cosmos.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void join(UserRequest.Join request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new NullPointerException("이미 가입된 이메일입니다.");
        });

        userRepository.save(request.toEntity());
    }
}
