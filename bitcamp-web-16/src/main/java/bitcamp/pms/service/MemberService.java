package bitcamp.pms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;

//서비스 클래스 구현하기

@Service
public class MemberService {
    @Autowired MemberDao memberDao;
    //의존객체 주입 

    public List<Member> list(int page, int size) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("startIndex", (page - 1) * size);
        params.put("pageSize", size);
        
        return memberDao.selectList(params);    
    }
    
    public Member get(String id){
        return memberDao.selectOne(id);
    }
    
    public int update(Member member) {
            
        return memberDao.update(member);
    }

    public int delete(String id) {
        return memberDao.delete(id);
    }

    public void add(Member member) {
        memberDao.insert(member);
    }
    
    public int getTotalPage(int size) {
        int count = memberDao.countAll();
        int totalPage = count/size;
        if(count % size>0)
            totalPage++;
        return totalPage;
        
        
    }
}
