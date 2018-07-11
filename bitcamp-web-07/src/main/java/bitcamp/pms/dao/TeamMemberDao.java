package bitcamp.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeamMemberDao {
    public int insert(String teamName, String memberId) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con.prepareStatement("insert into pms2_team_member(tnm,mid) values(?,?)");) {

            stmt.setString(1, teamName);
            stmt.setString(2, memberId);
            return stmt.executeUpdate();
        }
    }

    public int delete(String teamName, String memberId) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con.prepareStatement("delete from pms2_team_member where tnm=? and mid=?");) {

            stmt.setString(1, teamName);
            stmt.setString(2, memberId);
            return stmt.executeUpdate();
        }
    }
    
    public List<String> selectList(String teamName) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con.prepareStatement("select mid from pms2_team_member where tnm=?");) {

            stmt.setString(1, teamName);
            try (ResultSet rs = stmt.executeQuery()) {
                ArrayList<String> arr = new ArrayList<>();
                while (rs.next()) {
                    arr.add(rs.getString("mid"));
                }
                return arr;
            }
        }
    }

    public boolean isExist(String teamName, String memberId) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con
                        .prepareStatement("select mid from pms2_team_member where tnm=? and mid=?");) {

            stmt.setString(1, teamName);
            stmt.setString(2, memberId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
                return false;
            }
        }
    }
}
