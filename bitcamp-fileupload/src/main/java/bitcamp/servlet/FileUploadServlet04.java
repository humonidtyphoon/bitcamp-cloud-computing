package bitcamp.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


//servlet 3.0 부터 멀티파트 데이터를 처리하는 API 를 제공한다. 
// 멀티파트 데이터를 처리하는 멀티 파트 처리에 대한 정보를 
// 애노테이션 web.xml (DD파일) deployment descriptor)파일에 설정해야한다.

// 1. 애노테이션으로 설정하기
// 1MB 를 넘어가는 파일의 경우 메모리에 저장하지 말고 임시 폴터에 저장한다
@MultipartConfig(fileSizeThreshold=1024*1024,  //
        maxFileSize=1024*1024*2,
      //1개 파일의 최대 크기를 제한한다.
                maxRequestSize=1024*1024*2*10)
@WebServlet("/fileupload04")
public class FileUploadServlet04 extends HttpServlet {
    @Override
    protected void doPost(
            HttpServletRequest req, 
            HttpServletResponse resp) throws ServletException, IOException {
        
        try {
           String name = req.getParameter("name");
           String age = req.getParameter("age");
           Part photo = req.getPart("photo");
            
            
            // 새 파일명 준비
            String newfilename = UUID.randomUUID().toString(); 
            String path = this.getServletContext().getRealPath(
                    "/files/" + newfilename);
            
            photo.write(path);
            
        
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println("<html><head><title>파일업로드</title></head><body>");
            out.printf("name = %s<br>\n", name);
            out.printf("age = %s<br>\n", age);
            out.printf("photo = <a href='files/%s'>%s</a><br>\n", 
                    newfilename,
                    newfilename);
            out.printf("<p><img src='files/%s'></p>", newfilename);
            out.println("<p><img id='img1'></p>");
            out.println("<script>");
            out.println("    setTimeout(() => {");
            out.printf(
                    "        document.getElementById('img1').src = 'files/%s';", 
                    newfilename);
            out.println("    }, 1000);");
            out.println("</script>");
            out.println("</body></html>");
            
        } catch (Exception e) {
            e.printStackTrace();
    }
    }
}







