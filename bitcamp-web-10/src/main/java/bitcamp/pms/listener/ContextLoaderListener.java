package bitcamp.pms.listener;

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

            ClassPathXmlApplicationContext iocContainer = new ClassPathXmlApplicationContext("bitcamp/pms/config/application-context.xml");
            
            /*
            String resource = "bitcamp/pms/config/mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // System.out.println(sqlSessionFactory.toString());

            iocContainer.addBean("sqlSessionFactory", sqlSessionFactory);

            iocContainer.refresh();
                                            */
            
            ServletContext sc = sce.getServletContext();
            sc.setAttribute("iocContainer", iocContainer);

            /*
             * MemberDao memberDao = new MemberDao(sqlSessionFactory);
             * 
             * 
             * 
             * BoardDao boardDao = new BoardDao(sqlSessionFactory); ClassroomDao
             * classroomDao = new ClassroomDao(sqlSessionFactory); TeamDao teamDao = new
             * TeamDao(sqlSessionFactory); TaskDao taskDao = new TaskDao(sqlSessionFactory);
             * TeamMemberDao teamMemberDao = new TeamMemberDao(sqlSessionFactory);
             * 
             * ServletContext sc = sce.getServletContext(); //
             * sc.setAttribute("memberDao",memberDao);
             * 
             * //member sc.setAttribute("/member/list", new
             * MemberListController(memberDao)); sc.setAttribute("/member/view", new
             * MemberViewController(memberDao)); sc.setAttribute("/member/update", new
             * MemberUpdateController(memberDao)); sc.setAttribute("/member/delete", new
             * MemberDeleteController(memberDao)); sc.setAttribute("/member/add", new
             * MemberAddController(memberDao));
             * 
             * // sc.setAttribute("boardDao",boardDao); // board
             * sc.setAttribute("/board/list", new BoardListController(boardDao));
             * sc.setAttribute("/board/view", new BoardViewController(boardDao));
             * sc.setAttribute("/board/update", new BoardUpdateController(boardDao));
             * sc.setAttribute("/board/delete", new BoardDeleteController(boardDao));
             * sc.setAttribute("/board/add", new BoardAddController(boardDao));
             * 
             * //sc.setAttribute("classroomDao",classroomDao); // classroom
             * sc.setAttribute("/classroom/list", new
             * ClassroomListController(classroomDao)); sc.setAttribute("/classroom/view",
             * new ClassroomViewController(classroomDao));
             * sc.setAttribute("/classroom/update", new
             * ClassroomUpdateController(classroomDao));
             * sc.setAttribute("/classroom/delete", new
             * ClassroomDeleteController(classroomDao)); sc.setAttribute("/classroom/add",
             * new ClassroomAddController(classroomDao));
             * 
             * 
             * // sc.setAttribute("teamDao",teamDao); // TEAM
             * 
             * sc.setAttribute("/team/list", new TeamListController(teamDao));
             * sc.setAttribute("/team/view", new TeamViewController(teamDao));
             * sc.setAttribute("/team/update", new TeamUpdateController(teamDao));
             * sc.setAttribute("/team/delete", new
             * TeamDeleteController(teamDao,taskDao,teamMemberDao));
             * sc.setAttribute("/team/add", new TeamAddController(teamDao));
             * 
             * 
             * //sc.setAttribute("taskDao",taskDao); // TASK sc.setAttribute("/task/list",
             * new TaskListController(taskDao,teamDao)); sc.setAttribute("/task/view", new
             * TaskViewController(teamMemberDao,taskDao)); //
             * sc.setAttribute("/task/update", // new TeamUpdateController(teamDao)); //
             * sc.setAttribute("/task/delete", // new
             * TeamDeleteController(teamDao,taskDao,teamMemberDao));
             * sc.setAttribute("/task/add", new
             * TaskAddController(teamMemberDao,teamDao,taskDao));
             * 
             */

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
