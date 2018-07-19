package bitcamp.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.pms.domain.Task;
public interface TaskDao {
    
    public int delete(int no);
    List<Task> selectList(Map<String,Object> params);
   // public List<Task> selectList(String teamName);
    public int insert(Task task); 
    public int deleteByTeam(String teamName);
    public int update(Task task);
    public Task selectOne(int no);

}
