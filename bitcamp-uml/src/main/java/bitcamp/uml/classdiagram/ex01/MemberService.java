package bitcamp.uml.classdiagram.ex01;


// Association 연관   클래스 관계
// => 한 클래스가 다른 클래스를 지속적으로 사용하는 관계
// => 사용되는 객체를 "의존객체(Dependancy) "라고 부른다.
// 
public class MemberService {
    //MemberService 객체 가 사용하는 의존객체를 코드에서 표현할 때
    // 즉 MemberService 객체와 연관된 객체를 자바 코드로 표현할 때
    // 필드로 표현한다. 
    MemberDao memberDao;
    
    public MemberService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

}
