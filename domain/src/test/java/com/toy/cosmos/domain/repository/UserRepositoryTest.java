package com.toy.cosmos.domain.repository;

import com.toy.cosmos.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("domain-test")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;


    @Test
    public void findTest() {
        Long userId = 1L;

        User user = userRepository.findById(userId).get();

        Assert.assertEquals("a@naver.com", user.getEmail());
        System.out.println(user.getEmail());
    }
}
