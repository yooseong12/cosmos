package com.toy.cosmos.domain.repository;

import com.toy.cosmos.domain.common.Status;
import com.toy.cosmos.domain.entity.Board;
import com.toy.cosmos.domain.entity.Comment;
import com.toy.cosmos.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("domain-test")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void findTest() {
        Long userId = 1L;

        User user = userRepository.findById(userId).get();

        Assert.assertEquals("a@naver.com", user.getEmail());
    }

    @Test
    public void findAllUserWithUserFriendTest() {
        Long userId = 1L;
        Status.UserFriend status = Status.UserFriend.FOLLOW;

        List<User> users = userRepository.findAllUserWithUserFriend(userId, status);

        Assertions.assertEquals(2, users.size());
    }

    @Test
    public void deleteUser() {
        Long userId = 1L;
        Long friendId = 2L;

        long users = userRepository.deleteFriendIdByUserId(userId, friendId);

        Assertions.assertEquals(1, users);
    }

    @Test
    public void findBoard() {
        Long boardId = 1L;

        Board board = boardRepository.findById(boardId).get();

        Assert.assertEquals("새로운 제목1", board.getTitle());
    }

    @Test
    public void findComment() {
        Long commentId = 1L;

        Comment comment = commentRepository.findById(commentId).get();

        Assert.assertEquals("댓글1", comment.getContent());
    }

    @Test
    public void deleteUserTest() {
        Long id = 1L;

        userRepository.withdrawByUserId(id);
    }
}
