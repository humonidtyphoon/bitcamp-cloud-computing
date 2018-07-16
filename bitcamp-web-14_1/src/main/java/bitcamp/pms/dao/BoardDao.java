package bitcamp.pms.dao;

import java.util.List;

import bitcamp.pms.domain.Board;

public interface BoardDao {
    
    public int delete(int no); 
    public List<Board> selectList(); 
    public int insert(Board board); 
    public int update(Board board); 
    public Board selectOne(int no); 
}