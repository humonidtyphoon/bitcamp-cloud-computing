package bitcamp.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamMemberDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Task;


public class TaskViewController  {
    
    TeamMemberDao teamMemberDao;
    TaskDao taskDao;
    
    public TaskViewController(TeamMemberDao teamMemberDao, TaskDao taskDao) {
        super();
        this.teamMemberDao = teamMemberDao;
        this.taskDao = taskDao;
    }

    @RequestMapping
    public String view(
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