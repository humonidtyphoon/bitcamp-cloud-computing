// Controller 규칙에 따라 메서드 작성
package bitcamp.pms.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.ClassroomDao;
import bitcamp.pms.domain.Classroom;

@Controller("/classroom/add")
public class ClassroomAddController {

    ClassroomDao classroomDao;
    public ClassroomAddController() {}
    
    @Autowired
    public void setClassroomDao(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }
    public ClassroomAddController(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }

    @RequestMapping
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (request.getMethod().equals("GET")) {
            return "/classroom/form.jsp";
        }

        Classroom classroom = new Classroom();
        classroom.setTitle(request.getParameter("title"));
        classroom.setStartDate(Date.valueOf(request.getParameter("startDate")));
        classroom.setEndDate(Date.valueOf(request.getParameter("endDate")));
        classroom.setRoom(request.getParameter("room"));

        classroomDao.insert(classroom);
        
        return "redirect:list";

    }
}
