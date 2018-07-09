package bitcamp.pms.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;


@SuppressWarnings("serial")
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
    
    @Override
    protected void doPost(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        Member member = new Member();
        member.setId(request.getParameter("id"));
        member.setEmail(request.getParameter("email"));
        member.setPassword(request.getParameter("password"));

        System.out.println("새로 넣으러 왔다");

        response.setContentType("text/html;charset=UTF-8");
      
        
        try {
            MemberDao memberDao = (MemberDao)getServletContext().getAttribute("memberDao");
            memberDao.insert(member);   //add 메소드 호출 
            
            response.sendRedirect("list");
 
        } catch (Exception e) {
            request.setAttribute("error", e);
            System.out.println("<목록 가져오기 실패!");
            RequestDispatcher rd = 
                    request.getRequestDispatcher("/error.jsp");
            
            rd.forward(request, response);
            
            e.printStackTrace();
        }
        
    }

    
}
