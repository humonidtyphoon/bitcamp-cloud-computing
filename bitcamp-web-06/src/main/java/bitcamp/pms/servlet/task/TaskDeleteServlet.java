package bitcamp.pms.servlet.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;

@SuppressWarnings("serial")
@WebServlet("/task/delete")
public class TaskDeleteServlet extends HttpServlet {
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        String teamName = request.getParameter("teamName");
        
        try {
            TaskDao taskDao = (TaskDao)getServletContext().getAttribute("taskDao");
            int no = Integer.parseInt(request.getParameter("no"));
            int count = taskDao.delete(no);
            if (count == 0) {
                throw new Exception("해당 작업이 존재하지 않습니다.");
            }
            request.setAttribute("view","redirect:list?teamName="+ 
            URLEncoder.encode(teamName, "UTF-8"));
            
            
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
    }
    
}