package bitcamp.pms.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bitcamp.pms.annotation.RequestMapping;

@SuppressWarnings("serial")
@WebServlet(value="/app/*",loadOnStartup=1)
//서블릿이 생성하기 서버가 실행 할때 먼저 객체를 만들어서 확인한다.
public class DispatcherServlet extends HttpServlet {
    
    ApplicationContext iocContainer;
    
    @Override
    public void init() throws ServletException {
        //DispatcherSerlvet이 본격적으로 클라이언ㅌ 요청을 처리하기 전에 
        //Spring ContextLoaderListener 가 준
        
        iocContainer = 
                WebApplicationContextUtils.getWebApplicationContext
                (this.getServletContext());
        
        //IoC 컨테이너에 들어 있는 객체를 한번 출력해보자
        System.out.println("==================================");
        String [] names = iocContainer.getBeanDefinitionNames();
        for(String name:names) {
            System.out.printf("%s ==>%s\n",name,
                    iocContainer.getBean(name).getClass().getName());
        }
    
    
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("servlet Path 는??=============================>>" + request.getServletPath());
        System.out.println("===============================================");
        System.out.println("PathInfo 는??=============================>>" + request.getPathInfo());

        // Client 가 요청한 서비스 URl 을 알아낸다. ㅐ
        // 즉 /app/* 에서 해당하는 값을 추출한ㄷ.
        // ex) /app/member/list = > /member/list
        String pathInfo = request.getPathInfo();
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        // 다른 서블릿으로 위임
        ////////////////////////////////////////////////////////
        // servlet Context 보관소에 저장된 페이지 컨트롤러를 찾는다.
        // ServletContext sc = request.getServletContext();
//        Object pageController = 
//                getServletContext().getAttribute(pathInfo);

        
        // 스프링에 contextloaderListener 가 준비 한 IoC 컨테이너를 사용하려면
        // 다음과 같이 다른클래스에 도움을 받아서 IoC 컨테이너를 꺼내야한다.
        //  
//        ApplicationContext iocContainer = 
//                WebApplicationContextUtils.getWebApplicationContext
//                (this.getServletContext());
        
        // IoC 컨테이너에 

        try {

            // Ioc 컨테니어에 저장된 페이지 컨트롤러를 찾는다.
            Object pageController = iocContainer.getBean(pathInfo);

            // 페이지 컨트롤러 실행
            if (pageController == null)
                throw new Exception("해당 URL 에 대해 서비스를 처리할수 없습니다.");

            // page 컨트롤러에 있는 메서드 중에서 @RequestMapping 이라는
            // 애노테이션이 붙은 메서드를 찾아 호출한다.
            Method requestHandler = getRequestHandler(pageController.getClass());

            if (requestHandler == null)
                throw new Exception("요청핸들러를 찾지 못했습니다.");

            // 페이지 컨트롤러의 메서드를 호출한다.
            String view = (String) requestHandler.invoke(pageController, request, response);

            // String view = pageController.service(request, response);

            if (view.startsWith("redirect:")) {
                response.sendRedirect(view.substring(9));
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(view);
                rd.include(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);

        }

    }

    private Method getRequestHandler(Class<?> clazz) {

        // 클래스 정보에서 메서드 정보를 추출한다.
        Method[] methods = clazz.getMethods();

        // 메서드 중에 @RequestMapping 애노테이션이 붙은 메서드를 찾아낸다.
        for (Method m : methods) {
            RequestMapping anno = m.getAnnotation(RequestMapping.class);
            if (anno != null)
                return m;
        }
        return null;
    }

}
