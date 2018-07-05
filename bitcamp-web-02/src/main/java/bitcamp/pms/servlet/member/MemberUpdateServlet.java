package bitcamp.pms.servlet.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
    
    @Override
    protected void doPost(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        
        
        
        String id = request.getParameter("id");
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(id);
        System.out.println(email);
        System.out.println(password);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta http-equiv='Refresh' content='1;url=list'>");
        out.println("<title>회원 변경</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>회원 변경 결과</h1>");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (
                Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studydb",
                    "study", "1111");
                PreparedStatement stmt = con.prepareStatement(
                    "update pms2_member set email=?, pwd=sha2(?,224) where mid=?");) {
                
                stmt.setString(1, email);
                stmt.setString(2, password);
                stmt.setString(3, id);
            
            
            if (stmt.executeUpdate() == 0) {
                out.println("<p>해당 회원이 존재하지 않습니다.</p>");
            } else {
                out.println("<p>변경하였습니다.</p>");
                }
            }
        } catch (Exception e) {
            out.println("<p>변경 실패!</p>");
            e.printStackTrace(out);
        }
        out.println("</body>");
        out.println("</html>");
    }
}
