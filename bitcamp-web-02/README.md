# bitcamp-web-02 : 서블릿 

- pms2_member 테이블에 대한 CRUD 서블릿을 만들기

## package 생성 
bitcamp.pms.servlet 패키지 생성한다

## 회원 관리 서블릿 만들기
-servlet -api 의존 라이브러리 추가하기
(-mvnrepository.com) servlet -api 라이브러리 검색
 -build.gradle 에 라이브러리 등록 
 -'gradle eclipse 갱신 .classPath  파일 갱신
 -mysql jdbc 의존 라이브러리 추가하기
- bitcampt.pms.servlet.member
MemberAddServlet.java
MemberDeleteServlet.java
MemberListServlet.java
MemberUpdateServlet.java
MemberViewServlet.java