package bitcamp.pms.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.domain.Team;

public class TeamUpdateController implements PageController{
	    
    TeamDao teamDao;
    
	  public TeamUpdateController(TeamDao teamDao) {
        this.teamDao = teamDao;
    }
	  public String service(
	            HttpServletRequest request, 
	            HttpServletResponse response) throws Exception {
	            
	                Team team = new Team();
	                team.setName(request.getParameter("name"));
	                team.setDescription(request.getParameter("description"));
	                team.setMaxQty(Integer.parseInt(request.getParameter("maxQty")));
	                team.setStartDate(Date.valueOf(request.getParameter("startDate")));
	                team.setEndDate(Date.valueOf(request.getParameter("endDate")));
	                
	                if (teamDao.update(team) == 0) {
	                    throw new Exception("<p>해당 팀이 존재하지 않습니다.</p>");
	                }
	                return "redirect:list";
	            } 
	        }
	        
