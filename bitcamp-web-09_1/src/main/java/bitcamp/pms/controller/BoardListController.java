package bitcamp.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@Controller("/board/list")
public class BoardListController  {
    
    BoardDao boardDao;
    
    
    public BoardListController() {}
    
    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public BoardListController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @RequestMapping
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
