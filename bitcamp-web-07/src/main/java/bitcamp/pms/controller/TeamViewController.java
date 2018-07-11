package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.domain.Team;

public class TeamViewController implements PageController {

    TeamDao teamDao;

    public TeamViewController(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = request.getParameter("name");

        Team team = teamDao.selectOneWithMembers(name);
        if (team == null) {
            throw new Exception("유효하지 않은 팀입니다.");
        }
        request.setAttribute("team", team);

        return "/team/view.jsp";
    }
}
