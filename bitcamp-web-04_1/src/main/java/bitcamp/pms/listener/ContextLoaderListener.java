package bitcamp.pms.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import bitcamp.pms.dao.MemberDao;

//어노테이션을 통해서 속성 전달 
@WebListener  
public class ContextLoaderListener 
    implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 실행이 된다고 서버에서 호출을 할거다//////
        System.out.println("[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");
        System.out.println("[ContextLoaderListener 실행!!!!!");
        MemberDao memberDao = new MemberDao("jdbc:mysql://13.125.9.121:3306/studydb",
                "study", "1111");   
        
       ServletContext sc = sce.getServletContext();
       sc.setAttribute("memberDao",memberDao);
    }

//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        // TODO Auto-generated method stub
//        ServletContextListener.super.contextDestroyed(sce);
//    }
    
    
}
