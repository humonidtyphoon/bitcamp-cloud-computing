package bitcamp.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.domain.Team;
@Controller("/team/list")
public class TeamListController {
	
	    TeamDao teamDao;
	    public TeamListController() {}
	       
	    @Autowired
        public void setTeamDao(TeamDao teamDao) {
            this.teamDao = teamDao;
        }

        public TeamListController(TeamDao teamDao) {
            this.teamDao = teamDao;
        }
        @RequestMapping
        public String service(
                HttpServletRequest request, 
                HttpServletResponse response) throws Exception {
            
                List<Team> list = teamDao.selectList();
                request.setAttribute("list", list);
                
                return "/team/list.jsp";
                
          
        }
}
