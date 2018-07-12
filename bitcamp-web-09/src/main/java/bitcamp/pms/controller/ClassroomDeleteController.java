package bitcamp.pms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.dao.ClassroomDao;

public class ClassroomDeleteController {

    ClassroomDao classroomDao;

    public ClassroomDeleteController(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }
    @RequestMapping
    public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

        classroomDao.delete(Integer.parseInt(request.getParameter("no")));
        return "redirect:list";

    }
}
