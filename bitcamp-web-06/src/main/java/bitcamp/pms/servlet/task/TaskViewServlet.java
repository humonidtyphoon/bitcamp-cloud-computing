package bitcamp.pms.servlet.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamMemberDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Task;

@SuppressWarnings("serial")
@WebServlet("/task/view")
public class TaskViewServlet extends HttpServlet {
    
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        int no = Integer.parseInt(request.getParameter("no"));
        
        try {
            TaskDao taskDao = (TaskDao)getServletContext().getAttribute("taskDao");
            System.out.println("에러1");
            TeamMemberDao teamMemberDao = (TeamMemberDao)getServletContext().getAttribute("teamMemberDao");
            System.out.println("에러2");
            Task task = taskDao.selectOne(no);
            System.out.println(task+"선택된번호");
            if (task == null) {
                throw new Exception("해당 작업을 찾을 수 없습니다.");
            }
            System.out.println("에러3");
            List<Member> members = teamMemberDao.selectListWithEmail(task.getTeam().getName());
            System.out.println("에러4");
            request.setAttribute("task", task);
            System.out.println("에러5");
            request.setAttribute("members", members);
            System.out.println("에러6");
            request.setAttribute("view","/task/view.jsp");
            System.out.println("에러7");
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
    }
}