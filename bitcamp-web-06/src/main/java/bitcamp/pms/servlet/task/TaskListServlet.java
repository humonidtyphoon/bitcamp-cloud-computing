package bitcamp.pms.servlet.task;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.domain.Task;
import bitcamp.pms.domain.Team;

@SuppressWarnings("serial")
@WebServlet("/task/list")
public class TaskListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
       

        response.setContentType("text/html;charset=UTF-8");
        TaskDao taskDao = (TaskDao)getServletContext().getAttribute("taskDao");
        TeamDao teamDao = (TeamDao)getServletContext().getAttribute("teamDao");
        try {
            System.out.println("team task List");
            String teamName = request.getParameter("teamName");
            System.out.println("팀명은??:"+teamName);
            Team team = teamDao.selectOne(teamName);

            List<Task> list = taskDao.selectList(team.getName());
            System.out.println(list.toString());
            request.setAttribute("list", list);
            
            request.setAttribute("view","/task/list.jsp");

        } catch (Exception e) {
            request.setAttribute("error", e);
        }

    }
}
