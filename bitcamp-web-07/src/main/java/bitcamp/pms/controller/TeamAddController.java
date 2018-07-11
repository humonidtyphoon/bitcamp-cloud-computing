package bitcamp.pms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.domain.Team;

public class TeamAddController implements PageController {
    
    TeamDao teamDao;
    
    
    public TeamAddController(TeamDao teamDao) {
        this.teamDao = teamDao;
    }
    
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