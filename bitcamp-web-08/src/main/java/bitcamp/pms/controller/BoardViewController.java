package bitcamp.pms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

public class BoardViewController implements PageController {
    
    BoardDao boardDao;
    
   
    public BoardViewController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }


    public String service(
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        int no = Integer.parseInt(request.getParameter("no"));
        
            
            Board board = boardDao.selectOne(no);
            request.setAttribute("board", board);
            
            return "/board/view.jsp";
            
        }
    }



