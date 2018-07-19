package bitcamp.pms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.dao.TeamMemberDao;
import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Task;
import bitcamp.pms.domain.Team;


@Controller
@RequestMapping("/team/{teamName}/task")
public class TaskController {
    
    TeamDao teamDao;
    TaskDao taskDao;
    TeamMemberDao teamMemberDao;

    @RequestMapping("add")
    public String add(
            Task task,
            @PathVariable String teamName,
            @RequestParam("memberId") String memberId) throws Exception {
        
        task.setTeam(new Team().setName(teamName));
        task.setWorker(new Member().setId(memberId));
        
        Team team = teamDao.selectOne(task.getTeam().getName());
        if (team == null) {
            throw new Exception(task.getTeam().getName() + " 팀은 존재하지 않습니다.");
        }
        
        HashMap<String,Object> params = new HashMap<>();
        params.put("teamName", task.getTeam().getName());
        params.put("memberId", task.getWorker().getId());
        
        if (task.getWorker().getId().length() > 0 &&
            !teamMemberDao.isExist(params)) {
            throw new Exception(task.getWorker().getId() + "는 이 팀의 회원이 아닙니다.");
        }
        
        taskDao.insert(task);
        return "redirect:list";
        // 응답 헤더의 값으로 한글을 포함할 때는 
        // 서블릿 컨테이너가 자동으로 URL 인코딩 하지 않는다.
        // 위와 같이 개발자가 직접 URL 인코딩 해야 한다.
    }
    
    @RequestMapping("delete")
    public String delete(
            @RequestParam("no") int no,
            @PathVariable String teamName) throws Exception {
        
        int count = taskDao.delete(no);
        if (count == 0) {
            throw new Exception("해당 작업이 존재하지 않습니다.");
        }
        return "redirect:list";
        // 응답 헤더의 값으로 한글을 포함할 때는 
        // 서블릿 컨테이너가 자동으로 URL 인코딩 하지 않는다.
        // 위와 같이 개발자가 직접 URL 인코딩 해야 한다.
    }
    
    @RequestMapping("form")
    public String form(
            @PathVariable String teamName,
            Map<String,Object> map) throws Exception {
        
        Team team = teamDao.selectOne(teamName);
        if (team == null) {
            throw new Exception(teamName + " 팀은 존재하지 않습니다.");
        }
        List<Member> members = teamMemberDao.selectListWithEmail(teamName);
        map.put("members", members);
        map.put("teamName", teamName);
        return "task/form";
    }
    
    @RequestMapping("list{page}")
    public String list(
            @PathVariable String teamName,
            @MatrixVariable(defaultValue="1") int pageNo,
            @MatrixVariable(defaultValue="3") int pageSize,
            Map<String,Object> map) throws Exception {        
            
        HashMap<String,Object> params = new HashMap<>();
        params.put("startRowNo", (pageNo - 1) * pageSize);
        params.put("pageSize", pageSize);
        params.put("teamName", teamName);
        
        Team team = teamDao.selectOne(teamName);
        if (team == null) {
            throw new Exception(teamName + " 팀은 존재하지 않습니다.");
        }
        List<Task> list = taskDao.selectList(params);
        map.put("list", list);
        map.put("teamName", teamName);
        return "task/list";
    }
    
    @RequestMapping("update")
    public String update(
            Task task,
            @PathVariable String teamName,
            @RequestParam("memberId") String memberId) throws Exception {
        
        task.setTeam(new Team().setName(teamName));
        task.setWorker(new Member().setId(memberId));
        
        int count = taskDao.update(task);
        if (count == 0) {
            throw new Exception("<p>해당 작업이 없습니다.</p>");
        }
        return "redirect:list";
            // 응답 헤더의 값으로 한글을 포함할 때는 
            // 서블릿 컨테이너가 자동으로 URL 인코딩 하지 않는다.
            // 위와 같이 개발자가 직접 URL 인코딩 해야 한다.
    }
    
    @RequestMapping("{no}")
    public String view(
            @PathVariable String teamName,
            @PathVariable int no,
            Map<String,Object> map) throws Exception {
        
        Task task = taskDao.selectOne(no);
        if (task == null) {
            throw new Exception("해당 작업을 찾을 수 없습니다.");
        }
        
        List<Member> members = teamMemberDao.selectListWithEmail(
                task.getTeam().getName());
        
        map.put("task", task);
        map.put("members", members);
        map.put("teamName", teamName);
        return "task/view";
    }
}