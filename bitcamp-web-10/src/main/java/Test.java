import org.springframework.context.support.ClassPathXmlApplicationContext;

/**/
//
public class Test {
    public static void main(String[] args) throws Exception {
       ClassPathXmlApplicationContext iocContainer =
               new ClassPathXmlApplicationContext("bitcamp/pms/test/application-context.xml");
       
       System.out.println(iocContainer.getBeanDefinitionCount());
       String [] names= iocContainer.getBeanDefinitionNames();
       for(String name:names) {
           System.out.println(iocContainer.getBean(name).getClass().getName());
       }
  }
} 
