package bitcamp.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.pms.domain.Classroom;

public interface ClassroomDao {
    

    public int delete(int no); 
    
    public List<Classroom> selectList(); 
    
    public Classroom selectOne(int no); 

    public int insert(Classroom classroom); 

    public int update(Classroom classroom);
}
