INSERT INTO "user" ("id", "email", "password", "nickname", "phone", "created_at", "updated_at")
VALUES (1, 'a@naver.com', '1234', '김유성', '01012345677', '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       (2, 'b@naver.com', '1234', '김가영', '01012345678', '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       (3, 'c@naver.com', '1234', '강현준', '01012345679', '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       (4, 'd@naver.com', '1234', '홍길동', '01012345670', '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       (5, 'e@naver.com', '1234', '아무개', '01012345671', '2020-12-31 05:51:47', '2020-12-31 05:52:29');

INSERT INTO "user_friend" ("user_id", "friend_id", "status", "created_at", "updated_at")
VALUES (1, 2, 'FOLLOW', '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       (1, 3, 'FOLLOW', '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       (1, 4, 'BLOCKED', '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       (2, 1, 'FOLLOW', '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       (3, 2, 'BLOCKED', '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       (3, 1, 'BLOCKED', '2020-12-31 05:51:47', '2020-12-31 05:52:29');

INSERT INTO "board" ("title", "content", "hits", "liked", "user_id", "created_at", "updated_at")
VALUES ('새로운 제목1', '새로운 내용 1', 0, 0, 1, '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       ('새로운 제목2', '새로운 내용 2', 0, 0, 1, '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       ('새로운 제목3', '새로운 내용 3', 0, 0, 2, '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       ('새로운 제목4', '새로운 내용 4', 0, 0, 3, '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       ('새로운 제목5', '새로운 내용 5', 0, 0, 4, '2020-12-31 05:51:47', '2020-12-31 05:52:29');

INSERT INTO "comment" ("content", "board_id", "created_at", "updated_at")
VALUES ('댓글1', 1, '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       ('댓글2', 1, '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       ('댓글1', 2, '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       ('댓글2', 2, '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       ('댓글1', 4, '2020-12-31 05:51:47', '2020-12-31 05:52:29');