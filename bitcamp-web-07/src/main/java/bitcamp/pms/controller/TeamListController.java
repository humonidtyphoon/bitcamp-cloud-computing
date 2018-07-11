package bitcamp.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.domain.Team;
public class TeamListController implements PageController{
	
	    TeamDao teamDao;
	    
        public TeamListController(TeamDao teamDao) {
            this.teamDao = teamDao;
        }

        public String service(
                HttpServletRequest request, 
                HttpServletResponse response) throws Exception {
            
                List<Team> list = teamDao.selectList();
                request.setAttribute("list", list);
                
                return "/team/list.jsp";
                
          
        }
}
