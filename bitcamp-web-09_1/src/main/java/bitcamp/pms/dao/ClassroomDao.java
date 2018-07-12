package bitcamp.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Repository;
import bitcamp.pms.domain.Classroom;

@Repository
public class ClassroomDao {
    
    public ClassroomDao() {}
    
    SqlSessionFactory sqlSessionFactory;
    

    public ClassroomDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public int delete(int no) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            int count = sqlSession.delete(
                    "classroom.delete", no);
            sqlSession.commit();
            return count;
        } 
    }
    
    public List<Classroom> selectList() throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            return sqlSession.selectList(
                    "classroom.selectList");
        }
    }
    
    public Classroom selectOne(int no) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            return sqlSession.selectOne(
                    "classroom.selectOne", no);
        }
    }

    public int insert(Classroom classroom) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            int count = sqlSession.insert(
                    "classroom.insert", classroom);
            sqlSession.commit();
            return count;
        }
    }

    public int update(Classroom classroom) throws Exception {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession()) {
            int count = sqlSession.update(
                    "classroom.update", classroom);
            sqlSession.commit();
            return count;
        }
    }
}
