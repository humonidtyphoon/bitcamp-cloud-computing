package bitcamp.pms.servlet.team;

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

@SuppressWarnings("serial")
@WebServlet("/team/update")
public class TeamUpdateServlet extends HttpServlet{
	
	  @Override
	    protected void doPost(
	            HttpServletRequest request, 
	            HttpServletResponse response) throws ServletException, IOException {
	            
	            try {
	                TeamDao teamDao = (TeamDao) getServletContext().getAttribute("teamDao");
	                Team team = new Team();
	                team.setName(request.getParameter("name"));
	                team.setDescription(request.getParameter("description"));
	                team.setMaxQty(Integer.parseInt(request.getParameter("maxQty")));
	                team.setStartDate(Date.valueOf(request.getParameter("startDate")));
	                team.setEndDate(Date.valueOf(request.getParameter("endDate")));
	                
	                int count = teamDao.update(team);
	                if (count == 0) {
	                    throw new Exception("<p>해당 팀이 존재하지 않습니다.</p>");
	                }
	                request.setAttribute("view", "redirect:list");
	            } catch (Exception e) {
	                request.setAttribute("error", e);
	            }
	        }
	        
	    }