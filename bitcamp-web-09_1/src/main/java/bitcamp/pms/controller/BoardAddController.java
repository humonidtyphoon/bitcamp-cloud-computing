package bitcamp.pms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@Controller("/board/add")
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

    @RequestMapping
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
