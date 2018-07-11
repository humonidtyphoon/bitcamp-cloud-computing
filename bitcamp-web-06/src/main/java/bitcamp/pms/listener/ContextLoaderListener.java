package bitcamp.pms.listener;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.dao.ClassroomDao;
import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.dao.TaskDao;
import bitcamp.pms.dao.TeamDao;
import bitcamp.pms.dao.TeamMemberDao;

//어노테이션을 통해서 속성 전달 
@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 실행이 된다고 서버에서 호출을 할거다//////
        System.out.println("[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");
        System.out.println("[ContextLoaderListener 실행!!!!!");
        
        System.out.println("DAO 가 사용할 의존 객체 생성");
        //Dependency Injection 의존객체 생성
        
        try {
            String resource = "bitcamp/pms/config/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =
              new SqlSessionFactoryBuilder().build(inputStream);
            
            
            MemberDao memberDao = new MemberDao(sqlSessionFactory);
            BoardDao boardDao = new BoardDao(sqlSessionFactory);
            ClassroomDao classroomDao = new ClassroomDao(sqlSessionFactory);
            TeamDao teamDao = new TeamDao(sqlSessionFactory);
            TaskDao taskDao = new TaskDao(sqlSessionFactory);
            TeamMemberDao teamMemberDao = new TeamMemberDao(sqlSessionFactory);
            
           ServletContext sc = sce.getServletContext();
           sc.setAttribute("memberDao",memberDao);
           sc.setAttribute("boardDao",boardDao);
           sc.setAttribute("classroomDao",classroomDao);
           sc.setAttribute("teamMemberDao",teamMemberDao);
           sc.setAttribute("teamDao",teamDao);
           sc.setAttribute("taskDao",taskDao);
        }catch (Exception e) {e.printStackTrace();}

    }
}
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        // TODO Auto-generated method stub
//        ServletContextListener.super.contextDestroyed(sce);
//    }
    
    
