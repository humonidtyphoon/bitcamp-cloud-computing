package bitcamp.pms.servlet.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.dao.TeamMemberDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Task;
import bitcamp.pms.domain.Team;

@WebServlet("/task/add")
public class TaskAddServlet extends HttpServlet {
    
    TeamMemberDao teamMemberDao;

    TeamDao teamDao = (TeamDao)getServletContext().getAttribute("teamDao");
    TaskDao taskDao = (TaskDao)getServletContext().getAttribute("taskDao");
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        String teamName = request.getParameter("teamName");
        
        System.out.println("팀의 작업을 등록하러 왔단다");
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>작업 등록</title>");
        out.println("</head>");
        out.println("<body>");
        out.printf("<h1>'%s' 팀의 작업 등록</h1>\n", teamName);
        
        try {
            System.out.println(teamName+"팀네임은 무었???");
            Team team = teamDao.selectOne(teamName);
            if (team == null) {
                throw new Exception(teamName + " 팀은 존재하지 않습니다.");
            }
            
            out.println("<form action='add' method='post'>");
            out.printf("<input type='hidden' name='teamName' value='%s'>\n", teamName);
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("    <th>작업명</th><td><input type='text' name='title'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("    <th>시작일</th><td><input type='date' name='startDate'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("    <th>종료일</th><td><input type='date' name='endDate'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("    <th>작업자</th>");
            out.println("    <td>");
            out.println("        <select name='memberId'>");
            out.println("            <option value=''>--선택 안함--</option>");
            
            
            out.println("        </select>");
            out.println("    </td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("<button>등록</button>");
            out.println("</form>");

        } catch (Exception e) {
            System.out.println("그냥 뻑???");
            out.printf("<p>%s</p>\n", e.getMessage());
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</html>");
    }
    
    @Override
    protected void doPost(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
    String teamName = request.getParameter("teamName");
        
        try {
            Task task = new Task();
            task.setTitle(request.getParameter("title"));
            task.setStartDate(Date.valueOf(request.getParameter("startDate")));
            task.setEndDate(Date.valueOf(request.getParameter("endDate")));
            task.setTeam(new Team().setName(teamName));
            task.setWorker(new Member().setId(request.getParameter("memberId")));
            
            Team team = teamDao.selectOne(task.getTeam().getName());
            if (team == null) {
                throw new Exception(task.getTeam().getName() + " 팀은 존재하지 않습니다.");
            }
            
            if (task.getWorker().getId().length() > 0 &&
                !teamMemberDao.isExist(
                    task.getTeam().getName(), task.getWorker().getId())) {
                throw new Exception(task.getWorker().getId() + "는 이 팀의 회원이 아닙니다.");
            }
            
            taskDao.insert(task);
            
        } catch (Exception e) {
            request.setAttribute("error", e);
            request.setAttribute("title", "작업 등록 실패!");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

}
