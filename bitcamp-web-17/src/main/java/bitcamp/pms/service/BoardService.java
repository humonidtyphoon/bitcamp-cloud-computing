package bitcamp.pms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@Service    
public class BoardService {
    @Autowired
    BoardDao boardDao;

    public  List<Board> list(int page, int size) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("startIndex", (page - 1) * size);
        params.put("pageSize", size);

        return boardDao.selectList(params);
    }

    public int getTotalPage(int size) {
        int count = boardDao.countAll();
        int totalPage = count / size;
        if (count % size > 0)
            totalPage++;
        
        System.out.println(totalPage+"총페이지");
        System.out.println("coutn"+count);
        System.out.println(size+"사이즈");
        return totalPage;

    }

    public void add(Board board) {
        boardDao.insert(board);
    }

    public Board get(int no) {
        return boardDao.selectOne(no);
    }

    public int delete(int no) {
        return boardDao.delete(no);
    }

    public int update(Board board) {
        
        int count = boardDao.update(board);
        
        return count;
    }
    

}
