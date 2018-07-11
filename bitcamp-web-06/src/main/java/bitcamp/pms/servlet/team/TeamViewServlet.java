package bitcamp.pms.servlet.team;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.domain.Team;

@SuppressWarnings("serial")
@WebServlet("/team/view")
public class TeamViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       

            String name = request.getParameter("name");
            
            try {
                TeamDao teamDao = (TeamDao)getServletContext().getAttribute("teamDao");
                
                Team team = teamDao.selectOneWithMembers(name);
                if (team == null) {
                    throw new Exception("유효하지 않은 팀입니다.");
                }
                request.setAttribute("team", team);
                
                request.setAttribute("view","/team/view.jsp");
            } catch (Exception e) {
                request.setAttribute("error", e);
            }
        }
}
