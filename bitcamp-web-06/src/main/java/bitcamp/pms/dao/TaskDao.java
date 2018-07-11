package bitcamp.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.pms.domain.Task;

public class TaskDao {
    
    SqlSessionFactory sqlSessionFactory;

    public TaskDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


    public int delete(int no) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            int count = sqlSession.delete(
                    "task.delete", no);
            sqlSession.commit();
            return count;
        } 
    }

    
    public List<Task> selectList(String teamName) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            return sqlSession.selectList(
                    "task.selectList", teamName);
        }
    }

    public int insert(Task task) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            int count = sqlSession.insert(
                    "task.insert", task);
            sqlSession.commit();
            return count;
        }
    }

    public int update(Task task) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            int count = sqlSession.update(
                    "task.update", task);
            sqlSession.commit();
            return count;
        }
    }

    public Task selectOne(int no) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            return sqlSession.selectOne(
                    "task.selectOne", no);
        }
    }

}
