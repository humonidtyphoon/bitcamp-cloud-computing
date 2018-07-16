package bitcamp.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamMemberDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Task;

@Controller
public class TaskViewController {
    
    TeamMemberDao teamMemberDao;
    TaskDao taskDao;
    public TaskViewController() {}
    @Autowired
    public void setTeamMemberDao(TeamMemberDao teamMemberDao) {
        this.teamMemberDao = teamMemberDao;
    }
    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public TaskViewController(TeamMemberDao teamMemberDao, TaskDao taskDao) {
        super();
        this.teamMemberDao = teamMemberDao;
        this.taskDao = taskDao;
    }
    @RequestMapping("/task/view")
    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        int no = Integer.parseInt(request.getParameter("no"));
        
            System.out.println("에러1");
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
            return "/task/view.jsp";
            
     
    }
}