package bitcamp.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.pms.domain.Team;

public class TeamDao {
    
    SqlSessionFactory sqlSessionFactory;

    public TeamDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int delete(String name) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            int count = sqlSession.delete(
                    "team.delete", name);
            sqlSession.commit();
            return count;
        } 
    }
    
    public List<Team> selectList() throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            return sqlSession.selectList(
                    "team.selectList");
        }
    }

    public int insert(Team team) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            int count = sqlSession.insert(
                    "team.insert", team);
            sqlSession.commit();
            return count;
        }
    }

    public int update(Team team) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            int count = sqlSession.update(
                    "team.update", team);
            sqlSession.commit();
            return count;
        }
    }

    public Team selectOne(String name) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            return sqlSession.selectOne(
                    "team.selectOne", name);
        }
    } 
    }
