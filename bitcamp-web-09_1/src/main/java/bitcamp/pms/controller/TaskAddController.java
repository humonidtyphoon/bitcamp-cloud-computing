package bitcamp.pms.controller;


import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.dao.TeamMemberDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Task;
import bitcamp.pms.domain.Team;

@Controller("/task/add")
public class TaskAddController {
    
    TeamMemberDao teamMemberDao;
    TeamDao teamDao;
    TaskDao taskDao;
    
    public TaskAddController() {}
    @Autowired
    public void setTeamMemberDao(TeamMemberDao teamMemberDao) {
        this.teamMemberDao = teamMemberDao;
    }
    @Autowired
    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
    public TaskAddController(TeamMemberDao teamMemberDao, TeamDao teamDao, TaskDao taskDao) {
        super();
        this.teamMemberDao = teamMemberDao;
        this.teamDao = teamDao;
        this.taskDao = taskDao;
    }
    @RequestMapping  
    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        System.out.println("======================================= 여기서부터 에러가 난ㄷ. ");
        String teamName = request.getParameter("teamName");
        System.out.println("팀이믊"+teamName);

            Team team = teamDao.selectOne(teamName);
            if (team == null) {
                throw new Exception(teamName + " 팀은 존재하지 않습니다.");
            }
            if (request.getMethod().equals("GET")) {
                List<Member> members = teamMemberDao.selectListWithEmail(teamName);
                request.setAttribute("members", members);
                return "/task/form.jsp";
            }
            Task task = new Task();
            
            task.setTitle(request.getParameter("title"));
            task.setStartDate(Date.valueOf(request.getParameter("startDate")));
            task.setEndDate(Date.valueOf(request.getParameter("endDate")));
            task.setTeam(new Team().setName(teamName));
            task.setWorker(new Member().setId(request.getParameter("memberId")));
            
            Team team1 = teamDao.selectOne(task.getTeam().getName());
            if (team1 == null) {
                throw new Exception(task.getTeam().getName() + " 팀은 존재하지 않습니다.");
            }
            
            if (task.getWorker().getId().length() > 0 &&
                !teamMemberDao.isExist(
                    task.getTeam().getName(), task.getWorker().getId())) {
                throw new Exception(task.getWorker().getId() + "는 이 팀의 회원이 아닙니다.");
            }
            System.out.println("야여기서 에러 인거임1");
            taskDao.insert(task);
            System.out.println("야여기서 에러 인거임2");
            
           return "redirect:list?teamName="+URLEncoder.encode(teamName, "UTF-8");
    }

}
