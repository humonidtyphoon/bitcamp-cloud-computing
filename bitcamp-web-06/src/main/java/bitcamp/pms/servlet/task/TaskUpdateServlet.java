// Controller 규칙에 따라 메서드 작성
package bitcamp.pms.servlet.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.dao.TeamMemberDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Task;
import bitcamp.pms.domain.Team;

@SuppressWarnings("serial")
@WebServlet("/task/update")
public class TaskUpdateServlet extends HttpServlet {
    
        @Override
        protected void doPost(
                HttpServletRequest request, 
                HttpServletResponse response) throws ServletException, IOException {
            
            String teamName = request.getParameter("teamName");
            
            try {
                TaskDao taskDao = (TaskDao)getServletContext().getAttribute("taskDao");
                Task task = new Task()
                    .setNo(Integer.parseInt(request.getParameter("no")))
                    .setTitle(request.getParameter("title"))
                    .setStartDate(Date.valueOf(request.getParameter("startDate")))
                    .setEndDate(Date.valueOf(request.getParameter("endDate")))
                    .setState(Integer.parseInt(request.getParameter("state")))
                    .setTeam(new Team().setName(request.getParameter("teamName")))
                    .setWorker(new Member().setId(request.getParameter("memberId")));
                
                int count = taskDao.update(task);
                if (count == 0) {
                    throw new Exception("<p>해당 작업이 없습니다.</p>");
                }
                request.setAttribute("view", "redirect:list?teamName="
                +URLEncoder.encode(teamName, "UTF-8"));
                
            } catch (Exception e) {
                request.setAttribute("error", e);
            }
        }
    }
