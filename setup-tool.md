#프로그래밍 준비
## 개발 도구 설치
- openjDK 10.0.1
- eclipse photon
- visual studio code
- scoop (package manager)
- scoop install gradel
- scoop install mariadb
- scoop install nodejs
- scoop install mariadb@10.3.7

scoop services install \

## mysql 설정
```

서버에 접속한상태에서 
> mysql -uroot -p

mysql> update user set authentication_string=password('1111') where user='root';
권한갱신
mysql> flush privileges;

quit 종료

mysql> flush privileges; 
Enter password : (암호없기 때문에 그냥 친다)


애플리케이션에서 DB 에 사용할 user 를 추가 한다.
mysql> create user 'study'@'localhost' identified by '1111';

study 사용자가 사용할 데이터베이스를 생성

studydb 데이터베이스의 사용 권한을 study 사용자에게 부여한다.
mysql> grant all privileges on studydb.* to 'study'@'localhost';
모든 권한을 저 'study'@'localhost'; 에 부여

study 사용자로 접속

study 사용자가 사용할 수 있는 데이터베이스 목록 보기

사용할 데이터베이스 선택 

studydb 에 존재하는 테이블 목록보기

studydb에 테이블 생성

==> bitcamp -sql/pms-ddl.sql 실행



```

## eclpise
```
워크스페이스 설정
1) 문자집합을 UTF8 로 설정 한다.
    - Preferences/General/Workspace/Text file encoding
    -UTF-8로 설정한다.
2)에디터 기본 환경 설정
    - Preferences/General/Editors/Text Editors
    - 탭을 크기를 2 또는 4로 설정
    - 탭을 스페이스로 표현한다.
    - 한줄 크기는 80자 정도.
    - 탭이나 공백에 대해 흐릿하게 표시.
3)자바 설정
    -Preferences/JAVA/
        -Installed JRE : JDK 위치 지정하기
        - CODE Style/ Fomatter : 자바 에디터 탭 정보설정
        - compiler : 기본 컴파일 버전 설정 
4)WEB 환경설정
    - Preferences/Web/
        - CSS Files : 문자 집합을 UTF-8로 설정
        - HTML Files : 문자 집합을 UTF-8로 설정
        - JSP Files : 문자 집합을 UTF-8로 설정
5)WAS 서버 환경설정
    - servers/ 
        -Runtime Environments/
        - tomcat 경로 설정
6)톰캣 애플리케이션 배포하고 테스트할 실행 환경 설정
    -Server 뷰 에서 
        -server 
```

## 웹 프로젝트 폴더 준비 

```

1)예제 프로젝트 복사

-java106-java-project 를 
    BITCAMP-CLOUD-COMPUTING 폴더로 복사한다.
2) 프로젝트 폴더를 이클립스 프로젝트로 만든다.
- 'gradle ecliplse;'를 실행하여 이클립스 설정 파일을 생성한다.
- .project build.xml

```