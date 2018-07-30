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
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.pms.dao.ClassroomDao;
import bitcamp.pms.domain.Classroom;
import bitcamp.pms.service.ClassroomService;
@Controller
@RequestMapping("/classroom/")
public class ClassroomController {

    @Autowired ClassroomService classroomService;
    
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////list///////////////////////////////////
    @RequestMapping("list")
   public String list(
           @RequestParam(defaultValue="1")int page,
           @RequestParam(defaultValue="3")int size,
           Model model
           )
            throws Exception {
            List<Classroom> list = classroomService.list(page,size);
            // JSP가 게시물 목록을 사용할 수 있도록 ServletRequest 보관소에 저장한다.
            if(page<1)page=1;
            if(size<1||size >20)size =3;
            model.addAttribute("list", list);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            
            model.addAttribute("totalPage", classroomService.getTotalPage(size));
           return "classroom/list";

        } 
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////add///////////////////////////////////
    @GetMapping("form")
    public void form() {
    }
    @PostMapping("add")
    public String add(Classroom classroom) throws Exception {

        classroomService.add(classroom);
        
        return "redirect:list";

    }
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////update///////////////////////////////////
    @RequestMapping("update")
    public String update(Classroom classroom) throws Exception {
        int count = classroomService.update(classroom);
        if (count == 0) {
            throw new Exception("해당 강의가 존재하지 않습니다.");
        }
        return "redirect:list";
    }
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////delete///////////////////////////////////
    @RequestMapping("delete")
    public String delete(int no) throws Exception {
        classroomService.delete(no);
        
        return "redirect:list";

    }
    ///////////////////////////////////////////////////////////////////////
    ///////////////////////////////view///////////////////////////////////
    @RequestMapping("view/{no}")
     public String view(
            @PathVariable int no,Model model) throws Exception {
             Classroom classroom = classroomService.get(no);
             model.addAttribute("classroom", classroom);
             return "classroom/view";
     }
}
