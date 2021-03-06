package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;

@Controller("/member/update")
public class MemberUpdateController {
    MemberDao memberDao;
    
    public MemberUpdateController() {}
    public MemberUpdateController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    @RequestMapping
    public String update(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        String id = request.getParameter("id");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(id);
        System.out.println(email);
        System.out.println(password);
        response.setContentType("text/html;charset=UTF-8");

        Member member = new Member();
        member.setId(request.getParameter("id"));
        member.setEmail(request.getParameter("email"));
        member.setPassword(request.getParameter("password"));

        if (memberDao.update(member) == 0) {
            return "/member/updatefail.jsp";

        } else {
            return "redirect:list";

        }
    }

}
