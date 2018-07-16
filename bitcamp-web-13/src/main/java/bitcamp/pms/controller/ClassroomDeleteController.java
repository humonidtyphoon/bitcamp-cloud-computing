package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.ClassroomDao;

@Controller
public class ClassroomDeleteController {

    ClassroomDao classroomDao;
    public ClassroomDeleteController() {}
    @Autowired
    public void setClassroomDao(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }
    public ClassroomDeleteController(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }

    @RequestMapping("/classroom/delete")
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {

        classroomDao.delete(Integer.parseInt(request.getParameter("no")));
        return "redirect:list";

    }
}
