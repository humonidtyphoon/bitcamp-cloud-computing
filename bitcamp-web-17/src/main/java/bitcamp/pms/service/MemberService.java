package bitcamp.pms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bitcamp.pms.dao.MemberDao;
import bitcamp.pms.domain.Member;

//서비스 클래스 구현하기

@Service
public class MemberService {
    @Autowired
    MemberDao memberDao;
    // 의존객체 주입

    public List<Member> list(int page, int size) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("startIndex", (page - 1) * size);
        params.put("pageSize", size);

        return memberDao.selectList(params);
    }

    public Member get(String id) {
        return memberDao.selectOne(id);
    }
    // @Transactional(readOnly=true) 트랜잭션을 이용하여 제어 하기
    // 트랜잭션으로 오류로 인한 처리를 rollback 을 할수가 있다
    // 임시로 저장을 했다가 완료될때만 실제로 적용@@
    // @Transactional()
    // 메서드 호출이 예외없이 정상적으로 끝나면
    // DBMS 에 commit 을 요청하여 지금까지 한 작업을 실제 테이블에 적용시키낟,.
        // 애노테이션 대신 => 설정파일에 태그로 지정할수가 있다.
    public int update(Member member) {
        int count = memberDao.update(member);

        // return memberDao.update(member);
        if (count != 100)
            throw new RuntimeException("일부로 예외 발생 !!!!");
        return count;
    }

    public int delete(String id) {
        return memberDao.delete(id);
    }

    public void add(Member member) {
        memberDao.insert(member);
    }

    public int getTotalPage(int size) {
        int count = memberDao.countAll();
        int totalPage = count / size;
        if (count % size > 0)
            totalPage++;
        return totalPage;

    }
}
