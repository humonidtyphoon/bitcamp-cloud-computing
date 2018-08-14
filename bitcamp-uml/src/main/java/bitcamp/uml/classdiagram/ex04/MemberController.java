package bitcamp.uml.classdiagram.ex04;


//Dependency (의존)
// => 특정 메서드에서 일시적으로 사용하는 객체와의 관계
// => 보통 파라미터로 받은 객체를 사용할 때 의존 관계를 형성한다. 

// 실무!
// 실무에서는 association aggregation , composition ,dependency
// = > 관계를 따로 구분하지 않고 association 으로 표현한다.
// => 반드시 극 관계를 명확하게 표현하는 것이 더 나은 이해를 제공한다면
// => 그 때는 그 관계를 명시한다. 
// => 그러나 대부분은 관계가 모호한 경우가 많기 때문에 
// =>  더 명확히 표현하는 것이 오히려 이해를 방해하는 경우가 많다.
//     그래서 실무에서는 가능한 associtation 관계만 사용한다. 
public class MemberController {
    
    
    //즉 MemberController 랑 HttpSession '의존관계'이다.
    public String login(
            String id, 
            String pwd, 
            HttpSession session) {
        // Member 객체를 준비후
        Member member = new Member();
        
        // 로그인을 처리하기 위해 세션을 보관해야한다.
        
        session.setAttribute("login",member);
        
        return null;
        
    }
}
