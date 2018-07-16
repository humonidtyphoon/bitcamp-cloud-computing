package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.dao.TeamMemberDao;

@Controller
public class TeamDeleteController  {
		
        TeamDao teamDao;
        TaskDao taskDao;
        TeamMemberDao teamMemberDao;
        public TeamDeleteController() {}
        
      @Autowired
    public void setTeamDao(TeamDao teamDao) {
            this.teamDao = teamDao;
        }

      @Autowired
        public void setTaskDao(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

      @Autowired
        public void setTeamMemberDao(TeamMemberDao teamMemberDao) {
            this.teamMemberDao = teamMemberDao;
        }


    public TeamDeleteController(TeamDao teamDao, TaskDao taskDao, TeamMemberDao teamMemberDao) {
            this.teamDao = teamDao;
            this.taskDao = taskDao;
            this.teamMemberDao = teamMemberDao;
        }
    @RequestMapping("/team/delete")
    public String service(
	            HttpServletRequest request, 
	            HttpServletResponse response) throws Exception {
	        request.setCharacterEncoding("UTF-8");
	        String name = request.getParameter("name");
	        
	           
	            teamMemberDao.delete(name);
	            taskDao.deleteByTeam(name);
	            int count = teamDao.delete(name);
	            if (count == 0) {
	                throw new Exception ("해당 팀이 없습니다.");
	            }
	          return "redirect:list";
	            
	    }
	    
	}