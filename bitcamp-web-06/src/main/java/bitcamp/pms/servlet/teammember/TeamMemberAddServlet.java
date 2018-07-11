package bitcamp.pms.servlet.teammember;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.dao.TeamMemberDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Team;
@SuppressWarnings("serial")
@WebServlet("/team/member/add")
public class TeamMemberAddServlet extends HttpServlet {
    
//    TeamDao teamDao;
//    MemberDao memberDao;
//    TeamMemberDao teamMemberDao;
    @Override
    protected void doPost(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        String teamName = request.getParameter("teamName");
        String memberId = request.getParameter("memberId");
        
        try {
            TeamDao teamDao = (TeamDao)getServletContext().getAttribute("teamDao");
            MemberDao memberDao = (MemberDao)getServletContext().getAttribute("memberDao");
            TeamMemberDao teamMemberDao = (TeamMemberDao)getServletContext().getAttribute("teamMemberDao");
            Team team = teamDao.selectOne(teamName);
            if (team == null) {
                throw new Exception(teamName + " 팀은 존재하지 않습니다.");
            }
            Member member = memberDao.selectOne(memberId);
            if (member == null) {
                throw new Exception(memberId + " 회원은 없습니다.");
            }
            if (teamMemberDao.isExist(teamName, memberId)) {
                throw new Exception("이미 등록된 회원입니다.");
            }
            teamMemberDao.insert(teamName, memberId);
            
            request.setAttribute("view","../view?name="
                    + URLEncoder.encode(teamName,"UTF-8"));
            
            
        } catch (Exception e) {
            request.setAttribute("error", e);
        }
    }
    
}
