package bitcamp.pms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.Autowired;
import bitcamp.pms.annotation.Controller;
import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.BoardDao;
@Controller("/board/delete")
public class BoardDeleteController {
    
    BoardDao boardDao;
    public BoardDeleteController() {}
    
    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    public BoardDeleteController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @RequestMapping
    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
            int no = Integer.parseInt(request.getParameter("no"));
        
        
            
            boardDao.delete(no);
            return "redirect:list";
        
      
    }
    
} // class
