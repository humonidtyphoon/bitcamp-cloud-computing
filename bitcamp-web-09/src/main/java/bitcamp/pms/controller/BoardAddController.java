package bitcamp.pms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.annotation.RequestMapping;
import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

public class BoardAddController {
    
    BoardDao boardDao;
    
   
    public BoardAddController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @RequestMapping
    public String add(
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
