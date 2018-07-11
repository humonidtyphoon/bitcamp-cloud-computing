package bitcamp.pms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.controller.PageController;

@SuppressWarnings("serial")
@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet{
    
    @Override
    protected void service(HttpServletRequest request, 
            HttpServletResponse response) 
                    throws ServletException, IOException {
        
        
        System.out.println("servlet Path 는??=============================>>"+request.getServletPath());
        System.out.println("===============================================");
        System.out.println("PathInfo 는??=============================>>"+request.getPathInfo());
        
        // Client 가 요청한 서비스 URl 을 알아낸다. ㅐ
        // 즉 /app/* 에서 해당하는 값을 추출한ㄷ.
        // ex) /app/member/list = > /member/list
        String pathInfo = request.getPathInfo();
        response.setContentType("text/html;charset=UTF-8");
        
        // 다른 서블릿으로 위임 
        ////////////////////////////////////////////////////////
        //servlet Context 보관소에 저장된 페이지 컨트롤러를 찾는다.
        //ServletContext sc = request.getServletContext();
        PageController pageController = 
                (PageController)request.getServletContext().getAttribute(pathInfo);
        
        // 페이지 컨트롤러 실행
        try {
            if(pageController == null)
                   throw new Exception("해당 URL 에 대해 서비스를 처리할수 없습니다.");
            String view = pageController.service(request, response);
            if(view.startsWith("redirect:")) {
                response.sendRedirect(view.substring(9));
            } else{
                RequestDispatcher rd
                      = request.getRequestDispatcher(view);
                rd.include(request, response);
            }

        }catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
               
        }
       
    }

}
