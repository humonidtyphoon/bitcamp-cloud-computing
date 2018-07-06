package bitcamp.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import bitcamp.pms.domain.Classroom;

public class ClassroomDao {
    public static int delete(int no) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
            PreparedStatement stmt = con.prepareStatement(
                "delete from pms2_classroom where crno=?");) {
            
            stmt.setInt(1, no);
            return stmt.executeUpdate();
        } 
    }
    
    public static List<Classroom> selectList() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
            PreparedStatement stmt = con.prepareStatement(
                "select crno,titl,sdt,edt,room from pms2_classroom");
            ResultSet rs = stmt.executeQuery();) {
            
            ArrayList<Classroom> arr = new ArrayList<>();
            while (rs.next()) {
                Classroom classroom = new Classroom();
                classroom.setNo(rs.getInt("crno"));
                classroom.setTitle(rs.getString("titl"));
                classroom.setStartDate(rs.getDate("sdt"));
                classroom.setEndDate(rs.getDate("edt"));
                classroom.setRoom(rs.getString("room"));
                arr.add(classroom);
            }
            return arr;
        }
    }

    public static int insert(Classroom classroom) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
            PreparedStatement stmt = con.prepareStatement(
                "insert into pms2_classroom(titl,sdt,edt,room) values(?,?,?,?)");) {
            
            stmt.setString(1, classroom.getTitle());
            stmt.setDate(2, classroom.getStartDate(), Calendar.getInstance(Locale.KOREAN));
            stmt.setDate(3, classroom.getEndDate(), Calendar.getInstance(Locale.KOREAN));
            stmt.setString(4, classroom.getRoom());
        
            return stmt.executeUpdate();
        }
    }

    public static int update(Classroom classroom) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
            PreparedStatement stmt = con.prepareStatement(
                "update pms2_classroom set titl=?, sdt=?, edt=?, room=? where crno=?");) {
            
            stmt.setString(1, classroom.getTitle());
            stmt.setDate(2, classroom.getStartDate(), Calendar.getInstance(Locale.KOREAN));
            stmt.setDate(3, classroom.getEndDate(), Calendar.getInstance(Locale.KOREAN));
            stmt.setString(4, classroom.getRoom());
            stmt.setInt(5, classroom.getNo());
            return stmt.executeUpdate();
        }
    }

    public static Classroom selectOne(int no) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
            PreparedStatement stmt = con.prepareStatement(
                "select crno,titl,sdt,edt,room from pms2_classroom where crno=?");) {
            
            stmt.setInt(1, no);
            
            try (ResultSet rs = stmt.executeQuery();) {
                if (!rs.next()) 
                    return null;
                
                Classroom classroom = new Classroom();
                classroom.setNo(rs.getInt("crno"));
                classroom.setTitle(rs.getString("titl"));
                classroom.setStartDate(rs.getDate("sdt"));
                classroom.setEndDate(rs.getDate("edt"));
                classroom.setRoom(rs.getString("room"));
                return classroom;
            }
        }  
    }
}
