# bitcamp-cloud-computing
비트캠프 클라우드 컴퓨팅 과정

## web-01 : 웹 프로젝트 폴더 준비
- gradle을 이용하여 프로젝트 폴더를 만드는 방법 

## web-02 : 서블릿 + JDBC
- 서블릿으로 웹 애플리케이션을 만들고 배포하는 방법
- JDBC를 이용하여 데이터를 다루는 방법

## web-03 : DAO + DTO(VO) + ServletContextListener
- 데이터 처리를 전문으로 하는 DAO 객체를 도입
- ServletContextListener에서 DAO 객체를 준비한다.
- ServletContext 보관소에 DAO 객체를 저장한다.
- 서블릿은 ServletContext 보관소에서 DAO를 꺼내 사용한다.

## web-04 : 서블릿 + DAO + JSP = MVC 아키텍처
- JSP 기술을 사용하여 출력을 단순화하는 방법
- MVC 아키텍처의 개념 
-
## web-05: persistence Framework 도입
- DAO 에 mybatis 프레임워크를 적용하여 코드와 sql 문을 분리한다.
- 반복적으로 작성했던 JDBC 코드를 캡슐화 한다.

## web-06: Front Controller 도입
- 서블릿들이 공통으로 수행하는 작업을 프론트 컨트롤러에게 맡긴다
- 나머지 서블릿들은 "PAGE Controller"라고 부른다.
- 페이지 컨트롤러는 POJO 로 만들어도 된다.
## web-07: Page Controller 를 POJO 로 변환
- 프롵느 컨트롤러 가 동비외면ㅍ엩
## web-08: 애노테이션으로 요청 핸들러 다루기
-요청 핸들러(request Handler) : 클라이언트 요청이 들어왔을 때
호출 되는 메서드이다.  즉 클라이언트 요청을 처리하는 메서드
-인터페잇흐를 구현하는 대신에 애노테이션으로 요청 핸들러를 
표시하여 프론트 컨트롤러가 찾게 한다.
-이렇게 함으로써 페이지 컨트롤러를 만들 때 특정 인터페이스에 
종속되지 않게 한다.

