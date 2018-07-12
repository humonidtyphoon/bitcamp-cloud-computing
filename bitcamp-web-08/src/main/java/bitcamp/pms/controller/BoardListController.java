package bitcamp.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

public class BoardListController implements PageController {
    
    BoardDao boardDao;
    
    
    public BoardListController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }


    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
            
            System.out.println("1");
            List<Board> list = boardDao.selectList();
            System.out.println("2");
            
            request.setAttribute("list", list);
            System.out.println("3");
            
           return "/board/list.jsp";
            
    }
    

}
