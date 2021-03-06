package bitcamp.pms.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String id = request.getParameter("id");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(id);
        System.out.println(email);
        System.out.println(password);
        response.setContentType("text/html;charset=UTF-8");

        try {
            MemberDao memberDao = (MemberDao) getServletContext().getAttribute("memberDao");
            Member member = new Member();
            member.setId(request.getParameter("id"));
            member.setEmail(request.getParameter("email"));
            member.setPassword(request.getParameter("password"));

            if (memberDao.update(member) == 0) {
                
                RequestDispatcher rd = 
                        request.getRequestDispatcher("/member/updatefail.jsp");
                rd.forward(request, response);
                
            } else {
                response.sendRedirect("list");
                
            }
        } catch (Exception e) {
            request.setAttribute("error", e);
            RequestDispatcher rd = 
                    request.getRequestDispatcher("/error.jsp");
            
            rd.include(request, response);
            
            e.printStackTrace();
            
        }
    }

}
