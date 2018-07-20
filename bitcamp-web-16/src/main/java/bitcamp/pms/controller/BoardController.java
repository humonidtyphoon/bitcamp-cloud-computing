package bitcamp.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.pms.dao.BoardDao;
import bitcamp.pms.domain.Board;

@Controller
@RequestMapping("/board/")
public class BoardController  {
    
   @Autowired BoardDao boardDao;
   
   ////////////////////////////////////////////////////////
   /////////////////////list/////////////////////////////////
    @RequestMapping("list")
    public String list(
           Model model) throws Exception {
            List<Board> list = boardDao.selectList();
            model.addAttribute("list", list);
           return "board/list";
            
    } 
    ////////////////////////////////////////////////////////
    /////////////////////add/////////////////////////////////
    
    @GetMapping("form")
    public void form() {
        
    }
    @PostMapping("add")
    public String add(
        Board board) 
                    throws Exception {
            boardDao.insert(board);
            return "redirect:list";
        
    }
    ////////////////////////////////////////////////////////
    /////////////////////view/////////////////////////////////
    
    @RequestMapping("view/{no}")
    public String view(
           @PathVariable int no,Model model) throws Exception {
        Board board = boardDao.selectOne(no);
            model.addAttribute("board", board);
            return "board/view";
        }
////////////////////////////////////////////////////////
/////////////////////delete/////////////////////////////////
    
    @RequestMapping("delete")
    public String delete(int no
            ) throws Exception {
        
            boardDao.delete(no);
            return "redirect:list";
    }
////////////////////////////////////////////////////////
/////////////////////update/////////////////////////////////
    
    @RequestMapping("update")
    public String update(
         Board board) throws Exception {
            if (boardDao.update(board) == 0) {
                return "board/updatefail";
            } else {
                return "redirect:list";
            }
    }
}
