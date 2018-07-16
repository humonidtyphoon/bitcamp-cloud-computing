// Controller 규칙에 따라 메서드 작성
package bitcamp.pms.controller;

import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Task;
import bitcamp.pms.domain.Team;

@Controller("/task/update")
public class TaskUpdateController {

    TaskDao taskDao;

    public TaskUpdateController() {
    }

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public TaskUpdateController(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @RequestMapping
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String teamName = request.getParameter("teamName");

        Task task = new Task().setNo(Integer.parseInt(request.getParameter("no")))
                .setTitle(request.getParameter("title")).setStartDate(Date.valueOf(request.getParameter("startDate")))
                .setEndDate(Date.valueOf(request.getParameter("endDate")))
                .setState(Integer.parseInt(request.getParameter("state")))
                .setTeam(new Team().setName(request.getParameter("teamName")))
                .setWorker(new Member().setId(request.getParameter("memberId")));

        int count = taskDao.update(task);
        if (count == 0) {
            throw new Exception("<p>해당 작업이 없습니다.</p>");
        }
        return "redirect:list?teamName=" + URLEncoder.encode(teamName, "UTF-8");

    }
}
