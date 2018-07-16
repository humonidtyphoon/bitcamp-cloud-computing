package bitcamp.pms.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.pms.domain.Member;

public interface TeamMemberDao {
    
  
    public int insert(String teamName, String memberId); 
    
    public int delete(String teamName, String memberId);
    
    public int delete(String teamName);
    
    public List<String> selectList(String teamName);
    
    public List<Member> selectListWithEmail(String teamName);
    
    public boolean isExist(String teamName, String memberId); 
}