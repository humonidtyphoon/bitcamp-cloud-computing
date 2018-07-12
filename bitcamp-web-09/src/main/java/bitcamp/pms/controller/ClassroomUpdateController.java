package bitcamp.pms.controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.ClassroomDao;
import bitcamp.pms.domain.Classroom;

public class ClassroomUpdateController  {

    ClassroomDao classroomDao;

    public ClassroomUpdateController(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }
    @RequestMapping
    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Classroom classroom = new Classroom();
        classroom.setNo(Integer.parseInt(request.getParameter("no")));
        classroom.setTitle(request.getParameter("title"));
        classroom.setStartDate(Date.valueOf(request.getParameter("startDate")));
        classroom.setEndDate(Date.valueOf(request.getParameter("endDate")));
        classroom.setRoom(request.getParameter("room"));

        if (classroomDao.update(classroom) == 0) {
            return "/member/updatefail.jsp";

        } else {
            return "redirect:list";

        }
        
        
    }
}
