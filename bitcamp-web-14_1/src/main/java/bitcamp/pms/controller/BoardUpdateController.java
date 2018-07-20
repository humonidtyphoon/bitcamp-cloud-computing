package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;
@Controller
public class BoardUpdateController {
   
    BoardDao boardDao;
    public BoardUpdateController() {}
    
    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public BoardUpdateController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    @RequestMapping("/board/update")
    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        
            Board board = new Board();
            board.setTitle(request.getParameter("title"));
            board.setContent(request.getParameter("content"));
            board.setNo(Integer.parseInt(request.getParameter("no")));
            
   
            if (boardDao.update(board) == 0) {
                return "board/updatefail";

            } else {
                return "redirect:list";
            }
       
    }

    
} //class