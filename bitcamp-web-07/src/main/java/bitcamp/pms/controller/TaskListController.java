package bitcamp.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.domain.Task;
import bitcamp.pms.domain.Team;

public class TaskListController implements PageController {

    TaskDao taskDao;
    TeamDao teamDao;
    
    public TaskListController(TaskDao taskDao, TeamDao teamDao) {
        super();
        this.taskDao = taskDao;
        this.teamDao = teamDao;
    }
    public String service(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

       

        response.setContentType("text/html;charset=UTF-8");
            System.out.println("team task List");
            String teamName = request.getParameter("teamName");
            System.out.println("팀명은??:"+teamName);
            Team team = teamDao.selectOne(teamName);

            List<Task> list = taskDao.selectList(team.getName());
            System.out.println(list.toString());
            request.setAttribute("list", list);
            
           return "/task/list.jsp";

       

    }
}
