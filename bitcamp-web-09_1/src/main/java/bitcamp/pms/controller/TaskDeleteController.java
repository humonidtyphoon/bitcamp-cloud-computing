package bitcamp.pms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;

@Controller("/task/delete")
public class TaskDeleteController  {
    
    TaskDao taskDao;
    
    public TaskDeleteController() {}
    
    
    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }



    public TaskDeleteController(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
    @RequestMapping
    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
           String teamName = request.getParameter("teamName");
        
            int no = Integer.parseInt(request.getParameter("no"));
            int count = taskDao.delete(no);
            if (count == 0) {
                throw new Exception("해당 작업이 존재하지 않습니다.");
            }
           return "redirect:list?teamName="+ 
            URLEncoder.encode(teamName, "UTF-8");
            
    }
    
}