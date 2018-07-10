package bitcamp.pms.servlet.classroom;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.dao.ClassroomDao;

@SuppressWarnings("serial")
@WebServlet("/classroom/delete")
public class ClassroomDeleteServlet extends HttpServlet {
    
    
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {

        
        response.setContentType("text/html;charset=UTF-8");
        
            
            try {
                ClassroomDao classroomDao = (ClassroomDao)getServletContext().getAttribute("classroomDao");
                classroomDao.delete(Integer.parseInt(request.getParameter("no")));
                response.sendRedirect("list");
                
            } catch (Exception e) {
                request.setAttribute("error", e);
                request.setAttribute("title", "게시물 삭제 실패!");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
    }
}

