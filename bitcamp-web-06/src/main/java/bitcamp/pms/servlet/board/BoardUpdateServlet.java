package bitcamp.pms.servlet.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@SuppressWarnings("serial")
@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(
            HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        
        
        try {
            Board board = new Board();
            board.setTitle(request.getParameter("title"));
            board.setContent(request.getParameter("content"));
            board.setNo(Integer.parseInt(request.getParameter("no")));
            
            BoardDao boardDao = 
                    (BoardDao) getServletContext().getAttribute("boardDao");
            
            if (boardDao.update(board) == 0) {
                request.setAttribute("view","/board/updatefail.jsp");
            } else {
                request.setAttribute("view","redirect:list");
            }
                
        } catch (Exception e) {
            request.setAttribute("error", e);
           
        }
    }

    
} 
