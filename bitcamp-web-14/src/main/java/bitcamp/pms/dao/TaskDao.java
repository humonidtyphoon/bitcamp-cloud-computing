package bitcamp.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.pms.domain.Task;
public interface TaskDao {
    
    public int delete(int no);
    public List<Task> selectList(String teamName);
    public int insert(Task task);
    public int deleteByTeam(String teamName);
    public int update(Task task);
    public Task selectOne(int no);
}
