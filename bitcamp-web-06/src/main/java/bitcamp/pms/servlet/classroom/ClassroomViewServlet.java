package bitcamp.pms.servlet.classroom;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.ClassroomDao;
import bitcamp.pms.domain.Classroom;

@SuppressWarnings("serial")
@WebServlet("/classroom/view")
public class ClassroomViewServlet extends HttpServlet {

    
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {

        int no = Integer.parseInt(request.getParameter("no"));
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            ClassroomDao classroomDao = (ClassroomDao)getServletContext().getAttribute("classroomDao");
            Classroom classroom = classroomDao.selectOne(no);
            
            if (classroom == null) {
                throw new Exception("유효하지 않은 게시물 번호입니다.");
            }
            request.setAttribute("classroom", classroom);
            request.setAttribute("view", "/classroom/view.jsp");

            
        } catch (Exception e) {
            request.setAttribute("error", e);
           // request.setAttribute("title", "게시물 상세조회 실패!");
        }
    }
}
