package bitcamp.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.ClassroomDao;
import bitcamp.pms.domain.Classroom;

public class ClassroomListController {

    ClassroomDao classroomDao;
    
    
    public ClassroomListController(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }

    @RequestMapping
   public String list(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        response.setContentType("text/html;charset=UTF-8");
            
            List<Classroom> list = classroomDao.selectList();
            System.out.println("/.//////classroom_LIst 보러 왔다");

            // JSP가 게시물 목록을 사용할 수 있도록 ServletRequest 보관소에 저장한다.
            request.setAttribute("list", list);
            
           return "/classroom/list.jsp";

        } 
}
