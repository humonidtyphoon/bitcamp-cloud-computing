## ## web-15 : 요청 핸들러의 파라미터와 리턴 값 다루기
- 요청 핸드럴의 파라미터 다루기
- 요청 핸들러의 리턴 다루기

## /webapp 에 존재하는 JSP 파일을 /WEB-INF 폴더로 옮긴다.
- /member 폴더를 /WEB-INF/jsp 폴더로 옮기다.
- 프론트 컨트롤러(DispatcherServlet)에 기본으로 설정되어 있는 ViewResolver를 
   다른 뷰 리졸버(InternalResourceViewResolver)로 교체한다.
   
## 페이지 컨트롤러의 리턴 값을 변경한다.
- 옮겨진 JSP 파일의 경로와 InternalResourceViewResolver에 설정된 접두사/접미사에 
  맞춰 view 이름을 리턴한다.


## CRUD 메서드는 한개의 컨트롤러에 묶어 관리한다.
MemberXXXController  


