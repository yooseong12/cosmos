package com.toy.cosmos.api.service;

import com.toy.cosmos.api.model.request.UserRequest;
import com.toy.cosmos.api.model.response.UserResponse;
import com.toy.cosmos.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<UserResponse> read(Long id){
        userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return null; // 해결방법 모르겠음
    }
}
