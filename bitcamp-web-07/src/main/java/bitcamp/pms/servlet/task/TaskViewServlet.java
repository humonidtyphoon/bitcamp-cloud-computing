package bitcamp.pms.servlet.task;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.domain.Task;

@SuppressWarnings("serial")
@WebServlet("/task/view")
public class TaskViewServlet extends HttpServlet {
    
    
    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        TaskDao taskDao = (TaskDao)getServletContext().getAttribute("taskDao");
        try {
            int no = Integer.parseInt(request.getParameter("no"));
            
            Task task = taskDao.selectOne(no);
            if (task == null) {
                throw new Exception("해당 작업을 찾을 수 없습니다.");
            }
//            List<Member> members = teamMemberDao.selectListWithEmail(
//                    task.getTeam().getName());
            
            request.setAttribute("task", task);
//            request.setAttribute("members", members);
            
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("/task/view.jsp").forward(request, response);
//            List<Member> members = TeamMemberDao.selectListWithEmail(
//                    task.getTeam().getName());
            

            
//            for (Member member : members) {
//                out.printf("            <option %s>%s</option>\n",
//                        (member.equals(task.getWorker())) ? "selected" : "",
//                        member.getId());
//            }
            

        } catch (Exception e) {
            request.setAttribute("error", e);
            request.setAttribute("title", "작업 상세조회 실패!");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}

//ver 37 - 컨트롤러를 서블릿으로 변경
//ver 31 - JDBC API가 적용된 DAO 사용
//ver 28 - 네트워크 버전으로 변경
//ver 26 - TaskController에서 view() 메서드를 추출하여 클래스로 정의.
//ver 23 - @Component 애노테이션을 붙인다.
//ver 22 - TaskDao 변경 사항에 맞춰 이 클래스를 변경한다.
//ver 18 - ArrayList가 적용된 TaskDao를 사용한다.
//ver 17 - 클래스 생성
