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


@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        String id = request.getParameter("id");
        
        response.setContentType("text/html;charset=UTF-8");
 
        
        try {
            
            MemberDao memberDao = (MemberDao)getServletContext().getAttribute("memberDao");
              
            memberDao.delete(request.getParameter("id"));
            
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
