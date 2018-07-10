package bitcamp.pms.listener;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bitcamp.pms.dao.MemberDao;

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
            
           ServletContext sc = sce.getServletContext();
           sc.setAttribute("memberDao",memberDao);
           
        }catch (Exception e) {e.printStackTrace();}

    }
}
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        // TODO Auto-generated method stub
//        ServletContextListener.super.contextDestroyed(sce);
//    }
    
    
