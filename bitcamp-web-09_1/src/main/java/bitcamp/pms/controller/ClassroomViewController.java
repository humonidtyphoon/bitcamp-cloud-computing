package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.ClassroomDao;
import bitcamp.pms.domain.Classroom;
@Controller("/classroom/view")
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


   @RequestMapping
    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {

        int no = Integer.parseInt(request.getParameter("no"));
        
            Classroom classroom = classroomDao.selectOne(no);
            request.setAttribute("classroom", classroom);
           
            return "/classroom/view.jsp";

            
      
    }
}
