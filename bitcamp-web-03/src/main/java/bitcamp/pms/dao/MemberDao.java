package bitcamp.pms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bitcamp.pms.domain.Member;

public class MemberDao {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     String jdbcUrl;
     String username;
     String password;
    
    public MemberDao(
            String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }
    
    
    public List<Member> selectList() throws Exception {
        try (
             Connection con = DriverManager.getConnection(
                        jdbcUrl,username, password);
            PreparedStatement stmt = con.prepareStatement(
                "select mid, email from pms2_member");
            ResultSet rs = stmt.executeQuery();) {
            
            ArrayList<Member> list = new ArrayList<>();
            while (rs.next()) {
                
                Member member = new Member();
                member.setId(rs.getString("mid"));// 메모리를 다루는 연산자    
                member.setEmail(rs.getString("email"));
                
                list.add(member);
     
            }
            return list;
        }
    }
    
    public Member selectOne(String id) throws Exception{
        try (
              Connection con = DriverManager.getConnection(
                      jdbcUrl,username, password);
                PreparedStatement stmt = con.prepareStatement(
                    "select mid,email from pms2_member where mid=?");) {
                
                stmt.setString(1, id);
                
                try (ResultSet rs = stmt.executeQuery();) {
                        if (!rs.next()) 
                            return null;
                        
                        Member member = new Member();
                        member.setId(id);
                        member.setEmail(rs.getString("email"));
                        return member;
                    }
                    
        }
    }
  public int update(Member member) throws Exception {
        
        try (
            Connection con = DriverManager.getConnection(
                        jdbcUrl,username, password);
            PreparedStatement stmt = con.prepareStatement(
                "update pms2_member set email=?, pwd=sha2(?,224) where mid=?");) {
            
            stmt.setString(1, member.getEmail());
            stmt.setString(2, member.getPassword());
            stmt.setString(3, member.getId());
            return stmt.executeUpdate();
        
        }
    }
  public int delete(String id) throws Exception {
      try (
          Connection con = DriverManager.getConnection(
                      jdbcUrl,username, password);
          PreparedStatement stmt = con.prepareStatement(
              "delete from pms2_member where mid=?");
             ) {
          System.out.println("삭제가 되나??");
          stmt.setString(1, id);
          
      
      return stmt.executeUpdate();
  }
  }
  public int insert(Member member) throws Exception {
      try (
          Connection con = DriverManager.getConnection(
                      jdbcUrl,username, password);
          PreparedStatement stmt = con.prepareStatement(
              "insert into pms2_member(mid,email,pwd) values(?,?,?)");) {
          
          stmt.setString(1, member.getId());
          stmt.setString(2, member.getEmail());
          stmt.setString(3, member.getPassword());
      
          return stmt.executeUpdate();
      }
  }
  
}
