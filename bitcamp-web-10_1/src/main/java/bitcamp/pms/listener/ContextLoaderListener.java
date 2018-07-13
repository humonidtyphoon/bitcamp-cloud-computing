package bitcamp.pms.listener;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.support.ClassPathXmlApplicationContext;



//어노테이션을 통해서 속성 전달 
@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 실행이 된다고 서버에서 호출을 할거다//////
        System.out.println("[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");
        System.out.println("[ContextLoaderListener 실행!!!!!");

        System.out.println("DAO 가 사용할 의존 객체 생성");
        // Dependency Injection 의존객체 생성

        try {

            ClassPathXmlApplicationContext iocContainer =
                    new ClassPathXmlApplicationContext("bitcamp/pms/config/application-context.xml");
            
            

            ServletContext sc = sce.getServletContext();
            sc.setAttribute("iocContainer", iocContainer);

     

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
