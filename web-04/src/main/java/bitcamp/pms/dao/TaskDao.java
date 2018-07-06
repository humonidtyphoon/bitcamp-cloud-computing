package bitcamp.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import bitcamp.pms.domain.Member;
import bitcamp.pms.domain.Task;
import bitcamp.pms.domain.Team;

public class TaskDao {

    public static int delete(int no) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con.prepareStatement("delete from pms2_task where tano=?");) {

            stmt.setInt(1, no);
            return stmt.executeUpdate();
        }
    }

    public static List<Task> selectList(String teamName) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con
                        .prepareStatement("select tano,titl,sdt,edt,stat,mid from pms2_task where tnm=?");) {

            stmt.setString(1, teamName);
            try (ResultSet rs = stmt.executeQuery()) {
                ArrayList<Task> arr = new ArrayList<>();
                while (rs.next()) {
                    Task task = new Task();
                    task.setNo(rs.getInt("tano"));
                    task.setTitle(rs.getString("titl"));
                    task.setStartDate(rs.getDate("sdt"));
                    task.setEndDate(rs.getDate("edt"));
                    task.setState(rs.getInt("stat"));
                   // task.setWorker(new Member().setId(rs.getString("mid")));
                    arr.add(task);
                }
                return arr;
            }
        }
    }

    public int insert(Task task) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con
                        .prepareStatement("insert into pms2_task(titl,sdt,edt,mid,tnm) values(?,?,?,?,?)");) {

            stmt.setString(1, task.getTitle());
            stmt.setDate(2, task.getStartDate(), Calendar.getInstance(Locale.KOREAN));
            stmt.setDate(3, task.getEndDate(), Calendar.getInstance(Locale.KOREAN));
            stmt.setString(4, task.getWorker().getId());
            stmt.setString(5, task.getTeam().getName());
            return stmt.executeUpdate();
        }
    }

    public static int update(Task task) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con
                        .prepareStatement("update pms2_task set titl=?,sdt=?,edt=?,mid=?,tnm=? where tano=?");) {

            stmt.setString(1, task.getTitle());
            stmt.setDate(2, task.getStartDate(), Calendar.getInstance(Locale.KOREAN));
            stmt.setDate(3, task.getEndDate(), Calendar.getInstance(Locale.KOREAN));
            stmt.setString(4, task.getWorker().getId());
            stmt.setString(5, task.getTeam().getName());
            stmt.setInt(6, task.getNo());
            return stmt.executeUpdate();
        }
    }

    public static Task selectOne(int no) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con
                        .prepareStatement("select titl,sdt,edt,stat,mid,tnm from pms2_task where tano=?");) {

            stmt.setInt(1, no);

            try (ResultSet rs = stmt.executeQuery();) {
                if (!rs.next())
                    return null;

                Task task = new Task();
                task.setNo(no);
                task.setTitle(rs.getString("titl"));
                task.setStartDate(rs.getDate("sdt"));
                task.setEndDate(rs.getDate("edt"));
                task.setState(rs.getInt("stat"));
                task.setWorker(new Member().setId(rs.getString("mid")));
                task.setTeam(new Team().setName(rs.getString("tnm")));
                return task;
            }
        }
    }

    public int updateState(int no, int state) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://13.125.9.121:3306/studydb", "study", "1111");
                PreparedStatement stmt = con.prepareStatement("update pms2_task set stat=? where tano=?");) {

            stmt.setInt(1, state);
            stmt.setInt(2, no);
            return stmt.executeUpdate();
        }

    }
}
