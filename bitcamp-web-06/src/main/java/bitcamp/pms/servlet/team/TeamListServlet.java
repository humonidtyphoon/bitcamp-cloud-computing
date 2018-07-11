package bitcamp.pms.servlet.team;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.domain.Team;

@SuppressWarnings("serial")
@WebServlet("/team/list")
public class TeamListServlet extends HttpServlet{
	
	
        
        @Override
        protected void doGet(
                HttpServletRequest request, 
                HttpServletResponse response) throws ServletException, IOException {
            
            try {
                TeamDao teamDao = (TeamDao)getServletContext().getAttribute("teamDao");
                List<Team> list = teamDao.selectList();
                request.setAttribute("list", list);
                
                request.setAttribute("view","/team/list.jsp");
                
            } catch (Exception e) {
                request.setAttribute("error", e);
            }
        }
}
