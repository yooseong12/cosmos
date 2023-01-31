

# 회원/게시판/친구관리 API

**프로젝트 명칭 : cosmos**
이 토이 프로젝트는 공부 목적 프로젝트입니다.


## 프로젝트 개발 도구

-   SpringBoot `2.3.2.RELEASE` / gradle / JPA / QueryDsl / JAVA 11
-   AWS EB

데이터베이스 : MySQL

형상관리 툴 : GitHub

배포 주소 : [3.39.163.78](3.39.163.78)

<img width="1437" alt="스크린샷 2023-01-31 오후 3 23 14" src="https://user-images.githubusercontent.com/91926476/215682640-e0aa8b22-2896-48ee-9237-e46892b5e005.png">


## 주요 기능

> 제한사항 : 등록, 수정 삭제는 로그인 해야만 사용 가능

-   게시판( Board )
	- 게시판 등록
	- 게시판 조회
		- paging 처리
		- Status 가 NORMAL 인 게시판만 조회
	- 게시판 수정
		- 제목과 내용만 수정 가능
	- 게시판 삭제
		- Status를 DELETE로 변경
	- 댓글 등록
	- 댓글 삭제
-   유저 로그인 / 회원가입 ( User )
	- 유저 회원가입
	- 유저 로그인 - Token 발행
	- 회원 탈퇴
		- Status를 DELETE로 변경

- 친구 관리 ( Frined )
	- 친구 추가
	- 친구 조회
	- 친구 찾기
	- 친구 차단
		- Status를 BLOCKED로 변경
	- 친구 삭제
		- Status를 DELETE로 변경


## 프로젝트 회고
