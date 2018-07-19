package bitcamp.pms.controller;

import java.util.HashMap;
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

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;

@Controller
@RequestMapping("/member/")
public class MemberController{
    
    
    @Autowired MemberDao memberDao;
    
    
    ///////////////////////////////////////////////////////////////////
    ////////////////////////////list//////////////////////////////////
    @RequestMapping("list")
    public String list(
           @RequestParam(defaultValue="1")int page,
           @RequestParam(defaultValue="3")int size
           ,Model model) throws Exception {
        // DB 에서 가져올 데이터의 페이지 정보
        if(page<1)page=1;
        if(size<1||size >20)size =3;
        
        HashMap<String, Object> params = new HashMap<>();

           
            System.out.println(page + "page");
            System.out.println(size + "size?");
            params.put("startIndex", (page - 1) * size);
            params.put("pageSize", size);
        System.out.println("리스트 불러라");

        List<Member> list = memberDao.selectList(params);
        model.addAttribute("list", list);
        return "member/list";
    }
    
    ///////////////////////////////////////////////////////////////////
    ////////////////////////////add//////////////////////////////////
    @GetMapping("form")
    public void form() {
    }
    
    @PostMapping("add")
    public String add(Member member) throws Exception {
        
        memberDao.insert(member);
        return "redirect:list";
    }
    ///////////////////////////////////////////////////////////////////
    ////////////////////////////delete//////////////////////////////////
    @RequestMapping("delete")
    public String delete(String id) throws Exception {
        
        memberDao.delete(id);
        return "redirect:list";
       
    }
    ///////////////////////////////////////////////////////////////////
    ////////////////////////////update//////////////////////////////////
    @RequestMapping("update")
    public String update(Member member) throws Exception {
        
        if (memberDao.update(member) == 0) {
            return "member/updatefail";
        } else {
            return "redirect:list";
        }
    }
    ///////////////////////////////////////////////////////////////////
    ////////////////////////////view//////////////////////////////////
    @RequestMapping("view/{id}")
    public String view(@PathVariable String id,
            Model model) throws Exception {


        Member member = memberDao.selectOne(id);
        model.addAttribute("member", member);
        return "member/view";

    }

}
