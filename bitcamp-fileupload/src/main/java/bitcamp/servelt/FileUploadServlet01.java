package bitcamp.servelt;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@SuppressWarnings("serial")
@WebServlet("/fileupload01")
public class FileUploadServlet01 extends HttpServlet {
    @Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response) 
                    throws ServletException, IOException {
        
        // 일반 폼으로 전송된 한글 데이터가 꺠지지않고 유니코드로 바뀔때
        // 깨지지 않게 하려면 다음과 같이 한다.
       // getParameter()를 호출하기 전에 명령을 먼저 해야한ㄷ.
        // but multipart는 소용 없다.
       
        request.setCharacterEncoding("UTF-8");
        
        
        
     // 업로드 파일을 외장하드에 저장하는 역할을 수행
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 멀티파트 데이터를 파싱하여 
        //업로드된 파일은 위에서 설정한 factory를 이용하여
        // 데이터를 저장한다.
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 클라이언트가 보낸 데이터를 분석한다. 
        HashMap<String, Object> paramMap = new HashMap<>();
        
        
        try {
            List<FileItem> items = upload.parseRequest(request);
            for(FileItem item :items) {
                if(item.isFormField()) {// 일반 폼 데이터인경우
                    paramMap.put(item.getFieldName(), item.getString("UTF-8"));
                    // 입력된 폼은 
                }else {//파일데이터
                    // 새파일명 준비
                    String newfilename = UUID.randomUUID().toString();
                    
                    String path= this.getServletContext().getRealPath(
                            "/files/"+newfilename);
                    item.write(new File(path));
                    
                    paramMap.put(item.getFieldName(),
                            newfilename);
                }
            }
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        out.print("<html><head><title>파일업로드</title></head><body>");
        out.printf("name=%s",paramMap.get("name"));
        out.println();
        out.printf("age=%s",paramMap.get("age"));
        out.println();
        out.printf("photo=%s",paramMap.get("photo"));
        out.println();
        out.printf("<a href='files/%s'>%s</a><br>\n", 
                paramMap.get("photo"),
                paramMap.get("photo"));
        out.printf("<script>");
        out.printf("document.getElementsById('img1')factory.src=%s;");
        out.printf("</script>");
        out.printf("</body></html>");
        
        
        
    }
}
