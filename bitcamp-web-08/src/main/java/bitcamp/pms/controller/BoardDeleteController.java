package bitcamp.pms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;

public class BoardDeleteController implements PageController{
    
    BoardDao boardDao;
    
    
    public BoardDeleteController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
            int no = Integer.parseInt(request.getParameter("no"));
        
        
            
            boardDao.delete(no);
            return "redirect:list";
        
      
    }
    
} // class
