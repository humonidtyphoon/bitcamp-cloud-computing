package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.domain.Team;
@Controller
public class TeamViewController {

    TeamDao teamDao;
    public TeamViewController() {}
    
    @Autowired
    public void setTeamDao(TeamDao teamDao) {
        this.teamDao = teamDao;
    }
    public TeamViewController(TeamDao teamDao) {
        this.teamDao = teamDao;
    }
    @RequestMapping("/team/view")
    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = request.getParameter("name");

        Team team = teamDao.selectOneWithMembers(name);
        if (team == null) {
            throw new Exception("유효하지 않은 팀입니다.");
        }
        request.setAttribute("team", team);

        return "team/view";
    }
}
