import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import bitcamp.pms.annotation.Component;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.Repository;
import bitcamp.pms.context.ApplicationContext;

public class Test {
    
    static HashMap<String,Object> objPool = new HashMap<>();

    public static void main(String[] args) throws Exception {
//        ClassLoader defaultClassLoader = 
//                ClassLoader.getSystemClassLoader();
//        
//        URL url = defaultClassLoader.getResource(
//                "bitcamp/pms");
//        
//        File file = new File(url.toURI());
//        findClass(file,"bitcamp.pms");
        ApplicationContext iocContext = 
                new ApplicationContext("bitcamp.pms");
       
        Object obj = iocContext.getBean("/member/delete");
        
        System.out.println(obj.getClass().getName());
        
    }
    }
 
/*
    static void findClass(File path, String packageName) {
        //필터 설정
        // 필터에서 적용 
        // 디렉토리라면 파일이면서 이름에서 .class 인것만 가져온다.
        File[] subFiles = path.listFiles(
             (File pathname) -> {
                if(pathname.isDirectory())
                       return true;
                if(pathname.isFile()&&
                        pathname.getName().endsWith(".class"))
                    return true;
                return false;
        });
         // 파일 목록을 가져올때 필터로 걸려서 
        for(File subFile : subFiles) {
            if(subFile.isFile()) {
                String className=packageName+"."+
                          subFile.getName().replace(".class","");
                createObject(className);
                //.class-> 빈문자열로 대체 
            }else {
                findClass(subFile,packageName+"."+subFile.getName());
            }
        }
    }

    private static void createObject(String className) {
       try {
               //클래스 이름(패키지명+ 클래스명)으로 .class 파일을 찾아 로딩한다.
               Class<?> clazz = Class.forName(className);
               
               //Conponent , @Controller , @Repository 애노테이션이 
               // 붙은 클래스가 아니라면 객체를 생성하지 않는다.
               if(clazz.getAnnotation(Component.class) == null &&
                       clazz.getAnnotation(Controller.class) ==null &&
                       clazz.getAnnotation(Repository.class) ==null)
                   return;
                   
               
               // 객체를 저장할 때 사용할 이름을 알아낸다.
               String objName = getObjectName(clazz);

               
               //클래스 정보를 보고 기본 생성자를 알아낸다.
               Constructor<?> constructor = clazz.getConstructor();
               
               //기본 생성자를 호출하여 객체를 생성한 후 객체 보관소에 저장한다.
               objPool.put(objName,constructor.newInstance());
                   
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
    
        private static String getObjectName(Class<?> clazz)throws Exception{
            String objName =null;
           
            Component compAnno = 
                   clazz.getAnnotation(Component.class);
            if(compAnno !=null)
                objName = compAnno.value();
           
            
            Controller ctrlAnno = 
                    clazz.getAnnotation(Controller.class);
            
            if(ctrlAnno !=null)
                objName = ctrlAnno.value();
           
            Repository repAnno = 
                    clazz.getAnnotation(Repository.class);
            
            if(repAnno !=null)
                objName = repAnno.value();
            
            if(objName.length() ==0) {
                return clazz.getCanonicalName();
            }else {
                return objName;
            }
            
        }
    

}

/*  System.out.println(file.toString());
        System.out.println("절대경로//////////////////"+file.getAbsolutePath());
        System.out.println("디렉토리?인가/////////////"+file.isDirectory());
        /////////////////////////////////////////////
        String[] files = file.list();
        for(String fileName:files) {
            System.out.println(fileName);
        }
        System.out.println("");
        System.out.println(url.getPath()+"....InputStream");
        System.out.println(url.toString());*/