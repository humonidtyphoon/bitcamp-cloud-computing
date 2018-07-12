package bitcamp.pms.context;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Component;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.Repository;

public class ApplicationContext {
    
    HashMap<String, Object> objPool = new HashMap<>();
    
    
    public ApplicationContext(String packageName) throws Exception{
        
        String filePath = packageName.replace('.', '/');
        
        File dir = Resources.getResourceAsFile(filePath);
        
        findClass(dir,packageName);
        injectDependeny();
    }

    private void injectDependeny() throws Exception {
        //objPool 보관소에 저장된 모든 객체를 꺼낸다.
        Collection<Object> objList = objPool.values();
        
      for(Object obj: objList) {
          //객체의 클래스 정보를 추출한다.
          Class<?> clazz = obj.getClass();
          //해당클래스의 모든메서드를 추출한다.
          Method[] methods = clazz.getMethods();
            
          for(Method m:methods) {
              // 각 객체에 존재하는 메서드 중에서 @AutoWired 가 붙은 setter를 찾아낸다.
              // => setter 가 아니면 무시
              
              if(!m.getName().startsWith("set"))
                 continue;
              //Autowired 가 없으면 무시
              if(m.getAnnotation(Autowired.class) == null)
                  continue;
              //=> 파라미터가 한개가 아니라면 무시 
              if(m.getParameterTypes().length !=1)
                  continue;
        
              // setter 파라미터 타입을 알아낸다.
              Class<?> paramType = m.getParameterTypes()[0];    
              try {
              //setter 파라미터 타입에 해당하는 객체가 objPool 보관소에 꺼낸.
              Object dependency = getBean(paramType);
              //setter 를 호출하여 의존객체를 주입한다.
              m.invoke(obj, dependency);
              }catch(Exception e) {//의존 객체가 없으면 Setter 를 호출하지 않는다.
                  System.out.println("error"+e.getMessage());
                  }
              }
          }
        
    }
    
    
    public void refresh() throws Exception{
        injectDependeny();
    }
    
    
    public Object getBean(Class<?> type) {
        Collection<Object> objList = objPool.values();
        for(Object obj:objList) {
            if(type.isInstance(obj))
                return obj;
         }  
        throw new RuntimeException(type.getName()+"의 객체가 존재 하지 않습니다.");
    }
    
    
    public Object getBean(String name) {
        Object obj =
                objPool.get(name);
        if(obj ==null)
            throw new  RuntimeException(name+"이름의 객체가 존재하지 않습니다.");
        return obj;
    }
    
    public void addBean(String name, Object obj) {
        objPool.put(name, obj);
    }
    
    
    
    private void findClass(File path, String packageName) {
        // 필터 설정 및 필터에서 적용
        // 디렉토리라면 파일면서 class 만 가져오기
        File[] subFiles = path.listFiles(
                (File pathname) -> {
                    if(pathname.isDirectory())
                        return true;
                    if(pathname.isFile() &&
                            pathname.getName().endsWith(".class"))
                        return true;
                    return false; 
                });
        // 파일목록 가져올때 거르기
        for(File subFile:subFiles) {
            if(subFile.isFile()) {
                String className = packageName+"."+
                        subFile.getName().replace(".class", "");
                createObject(className);
            }else {
                findClass(subFile,packageName+"."+subFile.getName());
            }
        }
    }

    private void createObject(String className) {
        
        
        try {
            Class<?> clazz = Class.forName(className);
            
           //애노테이션 조건문 
            // component , Controller ,Repository 가
            //객체를 생성하지 않는다. 
            if(clazz.getAnnotation(Component.class) == null &&
                    clazz.getAnnotation(Controller.class) == null &&
                    clazz.getAnnotation(Repository.class)== null)
                return;
            
            //객체 의 저장 이름 알아내기
             
            String objName = getObjectName(clazz);
            //클래스 정보를 보고 기본 생성자를 알아낸다.
            Constructor<?> constructor = clazz.getConstructor();
            
            //기본 생성자를 호출하여 객체를 생성한 후 객체 보관소에 저장한다.
            objPool.put(objName,constructor.newInstance());
                    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    private String getObjectName(Class<?> clazz) {
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
