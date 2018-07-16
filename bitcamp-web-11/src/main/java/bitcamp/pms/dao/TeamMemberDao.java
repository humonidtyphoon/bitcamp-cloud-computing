package bitcamp.pms.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.pms.domain.Member;

@Repository
public class TeamMemberDao {
    
  public TeamMemberDao() {}
  
  
  SqlSessionFactory sqlSessionFactory;
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
}

    public TeamMemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    
    public int insert(String teamName, String memberId) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            HashMap<String,Object> paramMap = new HashMap<>();
            paramMap.put("teamName", teamName);
            paramMap.put("memberId", memberId);
            
            int count = sqlSession.insert(
                    "teamMember.insert", paramMap);
            sqlSession.commit();
            return count;
        }
    }
    
    public int delete(String teamName, String memberId) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            HashMap<String,Object> paramMap = new HashMap<>();
            paramMap.put("teamName", teamName);
            paramMap.put("memberId", memberId);
            
            int count = sqlSession.delete(
                    "teamMember.delete", paramMap);
            sqlSession.commit();
            return count;
        } 
    }
    
    public int delete(String teamName) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            HashMap<String,Object> paramMap = new HashMap<>();
            paramMap.put("teamName", teamName);
            
            int count = sqlSession.delete(
                    "teamMember.delete", paramMap);
            sqlSession.commit();
            return count;
        } 
    }
    
    public List<String> selectList(String teamName) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            return sqlSession.selectList(
                    "teamMember.selectList", teamName);
        }
    }
    
    public List<Member> selectListWithEmail(String teamName) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            System.out.println("???");
            return sqlSession.selectList(
                    "teamMember.selectListWithEmail",teamName);
        }
    }
    
    public boolean isExist(String teamName, String memberId) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            HashMap<String,Object> paramMap = new HashMap<>();
            paramMap.put("teamName", teamName);
            paramMap.put("memberId", memberId);
            
            int count = sqlSession.selectOne(
                    "teamMember.isExist", paramMap);
            if (count > 0)
                return true;
            else 
                return false;
        }
    }
}