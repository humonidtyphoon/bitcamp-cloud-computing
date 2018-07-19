package bitcamp.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.ClassroomDao;
import bitcamp.pms.domain.Classroom;
@Controller
@RequestMapping("/classroom/")
public class ClassroomController {

    @Autowired ClassroomDao classroomDao;
    
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////list///////////////////////////////////
    @RequestMapping("list")
   public String list(
           Model model
           )
            throws Exception {
            List<Classroom> list = classroomDao.selectList();
            // JSP가 게시물 목록을 사용할 수 있도록 ServletRequest 보관소에 저장한다.
            model.addAttribute("list", list);
           return "classroom/list";

        } 
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////add///////////////////////////////////
    @GetMapping("form")
    public void form() {
    }
    @PostMapping("add")
    public String add(Classroom classroom) throws Exception {

        classroomDao.insert(classroom);
        
        return "redirect:list";

    }
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////update///////////////////////////////////
    @RequestMapping("update")
    public String update(Classroom classroom) throws Exception {
        if (classroomDao.update(classroom) == 0) {
            return "classroom/updatefail";
        } else {
            return "redirect:list";
        }
    }
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////delete///////////////////////////////////
    @RequestMapping("delete")
    public String delete(int no) throws Exception {
        classroomDao.delete(no);
        return "redirect:list";

    }
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////view///////////////////////////////////
    @RequestMapping("view/{no}")
     public String view(
            @PathVariable int no,Model model) throws Exception {
             Classroom classroom = classroomDao.selectOne(no);
             model.addAttribute("classroom", classroom);
             return "classroom/view";
     }
}
