package bitcamp.pms.servlet.member;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;


@WebServlet("/member/list")

public class MemberListServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        //DB 에서 가져올 데이터의 페이지 정보 
        HashMap<String, Object> params= new HashMap<>();
        
        if(request.getParameter("page") !=null &&
              request.getParameter("size") != null){
            int page= Integer.parseInt(request.getParameter("page"));
            int size=Integer.parseInt(request.getParameter("size"));
            
            System.out.println(page+"page");
            System.out.println(size+"size?");
            
            params.put("startIndex",(page-1) * size);
            params.put("pageSize", size);
            
        }
        try {
            
           MemberDao memberDao = (MemberDao)getServletContext().getAttribute("memberDao");
                       
               //System.out.println("1");
               List<Member> list = memberDao.selectList(params);
               //System.out.println("2");
               System.out.println("리스트 불러라");
               request.setAttribute("list", list);
              //System.out.println("3");
               request.setAttribute("view","/member/list.jsp");
               
                   
          
        } catch (Exception e) {
            request.setAttribute("error", e);
            System.out.println("<목록 가져오기 실패!");
           
            e.printStackTrace();
        }
     
    }

}
