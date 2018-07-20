## web-16 :서비스 컴포넌트
- 페이지 컨트롤러에서 비즈니스 로직을 분리하여 별도의 클래스로 정의한다.
- 이클래스를 서비스 객체라 부른다.
- 서비스 객체는 비즈니스 로직과 트랜잭션 처리를 담당한다.

## 서비스 객체 생성
- application-context.xml에서 다음과 같이 설정을 변경한다.

    '''
    <mvc:annotation-driven enable-matrix-variables="true"/>
    '''

## 각 페이지 컨트롤러에 대해 요청 핸들러의  파라미터를 정리한다.

## 각 페이지 컨트롤러에 대해 요청 핸들러의  리턴 값을 정리한다.

## 각 페이지 컨트롤러에 대해 요청 핸들러의  애노테이션 정리한다.

## CRUD 메서드는 한 개의 컨트롤러에 묶어 관리한다.

-MemberXxxxController 들을 MemberController로 합친다.
