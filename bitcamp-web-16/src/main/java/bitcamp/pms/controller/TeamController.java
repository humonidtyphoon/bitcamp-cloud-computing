package bitcamp.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.dao.TeamMemberDao;
import bitcamp.pms.domain.Team;
@Controller
public class TeamController {
	
    @Autowired TeamDao teamDao;
    @Autowired TaskDao taskDao;
    @Autowired TeamMemberDao teamMemberDao;
    ////////////////////////////////////////////////////////
    ////////////////////////list///////////////////////////////
        @RequestMapping("/team/list")
        public String service(
               Model model) throws Exception {
                List<Team> list = teamDao.selectList();
                model.addAttribute("list", list);
                return "team/list";
        }
        ////////////////////////////////////////////////////////
        ////////////////////////add///////////////////////////////
        @GetMapping("/team/form")
        public void form() {
            
        }
        @RequestMapping("/team/add")
        public String add(Team team
                ) throws Exception {

                teamDao.insert(team);
                
                return "redirect:list";

        }
        ////////////////////////////////////////////////////////
        ////////////////////////update///////////////////////////////    
        @RequestMapping("/team/update")
        public String update(
                Team team) throws Exception {
                  
                      if (teamDao.update(team) == 0) {
                          throw new Exception("<p>해당 팀이 존재하지 않습니다.</p>");
                      }
                      return "redirect:list";
                  } 
        ////////////////////////////////////////////////////////
        ////////////////////////view///////////////////////////////
        @RequestMapping("/team/view/{name}")
        public String view(@PathVariable String name, Model model) throws Exception {


            Team team = teamDao.selectOneWithMembers(name);
            if (team == null) {
                throw new Exception("유효하지 않은 팀입니다.");
            }
            model.addAttribute("team", team);

            return "team/view";
        }
        ////////////////////////////////////////////////////////
        ////////////////////////delete///////////////////////////////
        @RequestMapping("/team/delete")
        public String delete(
                @RequestParam("name") String name) throws Exception {
                
                    //teamMemberDao.delete(name);
                    taskDao.deleteByTeam(name);
                    
                    if (teamDao.delete(name) == 0) {
                        throw new Exception ("해당 팀이 없습니다.");
                    }
                  return "redirect:list";
                    
            }
        
}
