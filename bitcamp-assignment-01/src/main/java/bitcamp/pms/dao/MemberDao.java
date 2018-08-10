package bitcamp.pms.dao;

import java.util.List;
import java.util.Map;

import bitcamp.pms.domain.Member;

public interface MemberDao {
    List<Member> selectList(Map<String,Object> params);
    Member selectOne(String id);
    Member login(String email);
    // 로그인 
    int update(Member member);   
    int delete(String id);
    int insert(Member member);  //회원 가입으로 쓰자 
    int insert2(Member member);  //회원 가입으로 쓰자 
    int countAll();
    
}



