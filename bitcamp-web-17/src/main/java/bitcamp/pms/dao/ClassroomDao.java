package bitcamp.pms.dao;

import java.util.List;
import java.util.Map;


import bitcamp.pms.domain.Classroom;

public interface ClassroomDao {
    

    public int delete(int no); 
    
    public List<Classroom> selectList(Map<String, Object> params); 
    public Classroom selectOne(int no); 
    public int insert(Classroom classroom); 
    public int update(Classroom classroom);
    public int countAll(); // =카운트 
}
