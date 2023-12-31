# Spring boot - 게시판 만들기
스프링 부트 + Board 게시판 사이트

## 💻 프로젝트 소개 
아주 기본적인 CRUD 게시판을 만들고, 기능을 하나씩 추가해 나간다.
<br>

## ⏰ 개발기간 
2023.11.13 ~ 2023.11.22(약 1주)
<br>

### 🔧 개요
- 프로젝트 명칭 : Basic Board Service
- 개발 인원 : 1명
- 주요 기능 : <br>
    ° 게시판 : CRDU 기능, 페이징 처리, 파일 업로드 <br>
    ° 댓글 : CRUD 기능
- 개발 언어 : JAVA 11
- 개발 환경 : SpringBoot 2.7.17, Jpa(Spring Data JPA)
- 데이터 베이스 : MariaDB
- 프론트엔드 : JSP
- 라이브러리 : Lombok
- 형상관리 툴 : Github
- 간단 소개 : 웹의 기본적인 게시판을 실습하며 구현한다

### 📖 프로젝트 주요 기능
1. 게시판 기능
   1) 글쓰기 (/board/save)
   2) 글목록 (/board/list)
      - 페이지 처리 (/board/list?page=?)
   3) 글조회 (/board/view/{id})
   4) 글수정 (/board/update/{id})
   5) 글삭제 (/board/delete/{id})
2. 댓글 기능
   1) 댓글 쓰기 (/comment/save)
