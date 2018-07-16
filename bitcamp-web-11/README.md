## web-11: Spring + Mybatis 연동하기
-기존에 직접만든 sqlSessionFactoryBean 대신에 Mybatis 가
제공하는 SqlSessionFactoryBean을 사용한다.

## 라이브러리 추가
- mvnrepository.com 에서 spring-context 라이브러를 찾는다.
- build.gradle에 의존 라이브러리 정보를 추가한다.
- 'gradle cleanEclipse'를 실행하여 기존 이클립스 설정을 제거한다.
- 'gradle eclipse'를 실행하여 이클립스 관련 설정 파일을 새로 만든다.
- 이때 추가한 의존 라이브러리가 자동으로 다운로드 될 것이다.
- 웹 프로젝트를 리프래시 하여 라이브러리 정보를 갱신한다. 

## Mybatis에서 SqlSessionFactoryBean 객체 사용하기
-기존의 SqlSessionFactoryBean 대신에 Mybaits-spring 에서 제공하는 클래스 사용
- Mybatis 를 spring과 연동하면 Datasource 는 spring 설정된 것을 사용해야한다.
-Mybatis 설정파일에 등록된 DataSource 는 사용하지 않는다.

##Spring 에 DataSource 등록하기
- mvnrepository.com 에서 Commons dbcp "검색하여 라이브러리를 찾는다.
- 라이브러리를 build.gradle 에 등록 한다.
-application-context.xml 스프링파일에 datasource 설정한다.
-Spring 에서 DataSource 를 설정 할 때는 "spring-jdbc "라이브러리를 추가해야한다.
-트랜잭션 관리자도 Spring 에 등록한다. 

## DispatcherServlet에서 Spring IoC 컨테이너를 사용하기
- 기존의 ApplicationContext 대신에 Spring IoC 컨테이너에 들어 있는 페이지 컨트롤러를 찾아 실행한다.

## DAO 구현체를 자동생성하는 MapperScannerConfigurer 등록하기
- Mybatis 에서 제공하는 DAO 구현체 자동 생성기를 등록하면 개발자 DAO 클랫를
직접 할 필요가 없다.
- 대신에 개발자느 DAO 인터페이스만 만들면 된다.

## 기존의 DAO 클래스를 인터페이스로 변경하기
- 기존의 작성된 DAO 클래스를 인터페이스로 변경한다.
- 단 인터페이스 명과 SQL 매퍼의 namespace 가 같게 해야 한다
- 인터페이스의 명과 SQL 의 id 도 같아야한다.
- 인터페이스의 메서드 파라미터는 한개여야 한다.
- 물론 메서드의 파라미터는 SQL 이름과 같아야 하다. 





