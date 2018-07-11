package bitcamp.pms.servlet.team;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.dao.TeamMemberDao;

@SuppressWarnings("serial")
@WebServlet("/team/delete")
public class TeamDeleteServlet extends HttpServlet {
		

	  @Override
	    protected void doGet(
	            HttpServletRequest request, 
	            HttpServletResponse response) throws ServletException, IOException {
	        request.setCharacterEncoding("UTF-8");
	        String name = request.getParameter("name");
	        
	        try {
	            TeamDao teamDao = (TeamDao) getServletContext().getAttribute("teamDao");
	            TaskDao taskDao = (TaskDao) getServletContext().getAttribute("taskDao");
	            TeamMemberDao teamMemberDao = (TeamMemberDao) getServletContext().getAttribute("teamMemebrDao");
	           
	            teamMemberDao.delete(name);
	            taskDao.deleteByTeam(name);
	            int count = teamDao.delete(name);
	            if (count == 0) {
	                throw new Exception ("해당 팀이 없습니다.");
	            }
	            request.setAttribute("view", "redirect:list");
	            
	        } catch (Exception e) {
	            request.setAttribute("error", e);
	        }
	    }
	    
	}