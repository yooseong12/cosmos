package com.toy.cosmos.auth.config;

import com.toy.cosmos.auth.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Slf4j
@Component("passwordEncoder")
@RequiredArgsConstructor
public class CustomPasswordEncoder implements PasswordEncoder {

    private final Pbkdf2Properties pbkdf2Properties;

    @Override
    public String encode(CharSequence rawPassword) {
        return BCrypt.hashpw(getCipherText(rawPassword), BCrypt.gensalt());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return BCrypt.checkpw(getCipherText(rawPassword), encodedPassword.replace("$2y$", "$2a$"));
    }

    @SneakyThrows({NoSuchAlgorithmException.class, InvalidKeySpecException.class})
    private String getCipherText(CharSequence rawPassword) {
        return AuthUtils.pbkdf2(
                rawPassword.toString(),
                pbkdf2Properties.getSalt(),
                pbkdf2Properties.getIterationCount(),
                pbkdf2Properties.getKeyLength()
        );
    }
}

/**
 * PasswordEncoder = 비밀번호를 안전하게 저장할 수 있도록 비밀번호의 단방향 암호화를 지원하는 interface
 *
 * @SneakyThrows = 메소드 선언부에 Throws 를 정의 하지 않아도, 검사된 예외를 Throw 할 수 있도록 하는 어노테이션
 * - 논쟁의 여지가 있는 어노테이션이기에 신중하게 사용해야 한다.
 * - 원칙적인 Java 메소드 선언부에 예외처리를 하는 것이 아니라서 특수한 경우에만 사용하는 것이 좋다.
 *
 * encode = 비밀번호를 단방향 암호화
 * matches = 암호화 되지 않은 비밀번호(raw-)와 암호화된 비밀번호(encoded-) 일치하는지 비교
 */