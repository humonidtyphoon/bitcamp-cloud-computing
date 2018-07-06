package bitcamp.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import bitcamp.pms.domain.Team;

public class TeamDao {

    public static int delete(String name) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con.prepareStatement("delete from pms2_team where name=?");) {

            stmt.setString(1, name);
            return stmt.executeUpdate();
        }
    }

    public static List<Team> selectList() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con.prepareStatement("select name, sdt, edt, max_qty from pms2_team");
                ResultSet rs = stmt.executeQuery();) {

            ArrayList<Team> arr = new ArrayList<>();
            while (rs.next()) {
                Team team = new Team();
                team.setName(rs.getString("name"));
                team.setStartDate(rs.getDate("sdt"));
                team.setEndDate(rs.getDate("edt"));
                team.setMaxQty(rs.getInt("max_qty"));
                arr.add(team);
            }
            return arr;
        }
    }

    public int insert(Team team) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con
                        .prepareStatement("insert into pms2_team(name,dscrt,max_qty,sdt,edt) values(?,?,?,?,?)");) {

            stmt.setString(1, team.getName());
            stmt.setString(2, team.getDescription());
            stmt.setInt(3, team.getMaxQty());
            stmt.setDate(4, team.getStartDate(), Calendar.getInstance(Locale.KOREAN));
            stmt.setDate(5, team.getEndDate(), Calendar.getInstance(Locale.KOREAN));
            return stmt.executeUpdate();
        }
    }

    public static int update(Team team) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con
                        .prepareStatement("update pms2_team set dscrt=?, max_qty=?, sdt=?, edt=? where name=?");) {

            stmt.setString(1, team.getDescription());
            stmt.setInt(2, team.getMaxQty());
            stmt.setDate(3, team.getStartDate(), Calendar.getInstance(Locale.KOREAN));
            stmt.setDate(4, team.getEndDate(), Calendar.getInstance(Locale.KOREAN));
            stmt.setString(5, team.getName());
            return stmt.executeUpdate();
        }
    }

    public static Team selectOne(String name) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
            PreparedStatement stmt = con.prepareStatement(
                "select dscrt, sdt, edt, max_qty from pms2_team where name=?");) {
            
            stmt.setString(1, name);
            
            try (ResultSet rs = stmt.executeQuery();) {
                if (!rs.next()) 
                    return null;
                
                Team team = new Team();
                team.setName(name);
                team.setDescription(rs.getString("dscrt"));
                team.setStartDate(rs.getDate("sdt"));
                team.setEndDate(rs.getDate("edt"));
                team.setMaxQty(rs.getInt("max_qty"));
                return team;
            }
        }  

    }
    }
