INSERT INTO "user" ("email", "password", "nickname", "phone", "created_at", "updated_at")
VALUES ('a@naver.com', '1234', '김유성', '01012345677', '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       ('b@naver.com', '1234', '김가영', '01012345678', '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       ('c@naver.com', '1234', '강현준', '01012345679', '2020-12-31 05:51:47', '2020-12-31 05:52:29');

INSERT INTO "user_friend" ("user_id", "friend_id", "status", "created_at", "updated_at")
VALUES (1, 2, 'FOLLOW', '2020-12-31 05:51:47', '2020-12-31 05:52:29'),
       (1, 3, 'FOLLOW', '2020-12-31 05:51:47', '2020-12-31 05:52:29');