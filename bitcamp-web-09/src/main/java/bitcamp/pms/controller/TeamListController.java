package bitcamp.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.domain.Team;
public class TeamListController{
	
	    TeamDao teamDao;
	    
        public TeamListController(TeamDao teamDao) {
            this.teamDao = teamDao;
        }

        @RequestMapping
        public String list(
                HttpServletRequest request, 
                HttpServletResponse response) throws Exception {
            
                List<Team> list = teamDao.selectList();
                request.setAttribute("list", list);
                
                return "/team/list.jsp";
                
          
        }
}
