package bitcamp.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.pms.domain.Member;
import bitcamp.pms.service.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController{
    
    @Autowired MemberService memberService;
    
    
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
        
        List<Member> list = memberService.list(page,size);
        
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        
        model.addAttribute("totalPage", memberService.getTotalPage(size));
        return "member/list";
    }
    
    ///////////////////////////////////////////////////////////////////
    ////////////////////////////add//////////////////////////////////
    @GetMapping("form")
    public void form() {
    }
    
    @PostMapping("add")
    public String add(Member member) throws Exception {
        
        memberService.add(member);
        
        return "redirect:list";
    }
    ///////////////////////////////////////////////////////////////////
    ////////////////////////////delete//////////////////////////////////
    @RequestMapping("delete")
    public String delete(String id) throws Exception {
        
        
        memberService.delete(id);
        return "redirect:list";
       
    }
    ///////////////////////////////////////////////////////////////////
    ////////////////////////////update//////////////////////////////////
    @RequestMapping("update")
    public String update(Member member) throws Exception {
        
        if (memberService.update(member) == 0) {
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


        Member member = memberService.get(id);
        //memberService.get(id);
        model.addAttribute("member", member);
        return "member/view";

    }

}
