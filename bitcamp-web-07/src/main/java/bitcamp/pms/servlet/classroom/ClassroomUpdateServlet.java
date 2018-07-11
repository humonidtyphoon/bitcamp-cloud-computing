package bitcamp.pms.servlet.classroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.dao.ClassroomDao;
import bitcamp.pms.domain.Classroom;

@SuppressWarnings("serial")
@WebServlet("/classroom/update")
public class ClassroomUpdateServlet extends HttpServlet {
    
    @Override
    protected void doPost(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        Classroom classroom = new Classroom();
        ClassroomDao classroomDao = (ClassroomDao)getServletContext().getAttribute("classroomDao");
        classroom.setNo(Integer.parseInt(request.getParameter("no")));
        classroom.setTitle(request.getParameter("title"));
        classroom.setStartDate(Date.valueOf(request.getParameter("startDate")));
        classroom.setEndDate(Date.valueOf(request.getParameter("endDate")));
        classroom.setRoom(request.getParameter("room"));
        
        response.setContentType("text/html;charset=UTF-8");
        
            try {
                
                if (classroomDao.update(classroom) == 0) {
                    throw new Exception("해당 게시물이 존재하지 않습니다.");
                } 
                response.sendRedirect("list");
                
            } catch (Exception e) {
                request.setAttribute("error", e);
                request.setAttribute("title", "게시물 변경 실패!");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }
}

