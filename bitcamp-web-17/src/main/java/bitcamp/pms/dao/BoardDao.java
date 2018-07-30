package bitcamp.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.pms.domain.Board;

public interface BoardDao {
    
    public int delete(int no); 
    public List<Board> selectList(Map<String, Object> params); 
    public int insert(Board board); 
    public int update(Board board); 
    public Board selectOne(int no); 
    
    public int countAll(); // =카운트 
}