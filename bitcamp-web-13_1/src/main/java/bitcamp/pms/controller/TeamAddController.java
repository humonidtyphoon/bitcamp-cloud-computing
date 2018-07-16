package bitcamp.pms.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.domain.Team;

@Controller
public class TeamAddController  {
    
    TeamDao teamDao;
    public TeamAddController() {}
    
    @Autowired
    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }
    public TeamAddController(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @RequestMapping("/team/add")
    public String service(HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            return "/team/form.jsp";
        }
   
            Team team = new Team();
            team.setName(request.getParameter("name"));
            team.setDescription(request.getParameter("description"));
            team.setMaxQty(Integer.parseInt(request.getParameter("maxQty")));
            team.setStartDate(Date.valueOf(request.getParameter("startDate")));
            team.setEndDate(Date.valueOf(request.getParameter("endDate")));

            teamDao.insert(team);
            
            return "redirect:list";

    }

}