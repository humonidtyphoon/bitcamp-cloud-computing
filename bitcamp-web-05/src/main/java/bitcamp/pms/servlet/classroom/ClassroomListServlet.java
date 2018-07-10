package bitcamp.pms.servlet.classroom;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.ClassroomDao;
import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Classroom;

@SuppressWarnings("serial")
@WebServlet("/classroom/list")
public class ClassroomListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try {
            
            ClassroomDao classroomDao = (ClassroomDao)getServletContext().getAttribute("classroomDao");
            List<Classroom> list = classroomDao.selectList();
            System.out.println("/.//////classroom_LIst 보러 왔다");

            // JSP가 게시물 목록을 사용할 수 있도록 ServletRequest 보관소에 저장한다.
            request.setAttribute("list", list);

            RequestDispatcher rd = request.getRequestDispatcher("/classroom/list.jsp");

            rd.include(request, response);

        } catch (Exception e) {
            request.setAttribute("error", e);
            request.setAttribute("title", "게시물 목록조회 실패!");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

    }
}
