package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.ClassroomDao;
import bitcamp.pms.domain.Classroom;
@Controller
public class ClassroomViewController  {
    
    ClassroomDao classroomDao;
    public ClassroomViewController() {}
    @Autowired
    public void setClassroomDao(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }
   public ClassroomViewController(ClassroomDao classroomDao) {
        this.classroomDao = classroomDao;
    }


   @RequestMapping("/classroom/view")
    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        int no = Integer.parseInt(request.getParameter("no"));
        
            Classroom classroom = classroomDao.selectOne(no);
            request.setAttribute("classroom", classroom);
           
            return "/classroom/view.jsp";

            
      
    }
}
