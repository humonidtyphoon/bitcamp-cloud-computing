package bitcamp.pms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@Controller
public class BoardAddController {
    
    BoardDao boardDao;
    public  BoardAddController() {}
    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    public BoardAddController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @RequestMapping("/board/add")
    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) 
                    throws Exception {
        
            if(request.getMethod().equals("GET")) {
                return "/board/form.jsp";
            }

            Board board = new Board();
            board.setTitle(request.getParameter("title"));
            board.setContent(request.getParameter("content"));
            
            
            boardDao.insert(board);
            
            return "redirect:list";
        
    }
    

}
