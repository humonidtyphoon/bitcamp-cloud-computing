package bitcamp.pms.config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정파일이라는 것에
@Configuration
public class AppConfig {
    public AppConfig() {
        System.out.println("AppConfig() 호출");
    }
    /*
    @Bean("sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        System.out.println("AppConfig.SqlSessionFactory()호출됨 ");
    
        String resource = "bitcamp/pms/config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
       return 
          new SqlSessionFactoryBuilder().build(inputStream);
        
            
    }
    */
}
