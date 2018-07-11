package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Front Controller (Caller)와 PageController(called) 사이의 호출 규칙 !

public interface PageController {
    
    String service(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception;

}
