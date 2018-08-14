package bitcamp.uml.classdiagram.ex03;
//Composition (합성)
//=> 한객체가 다른 객체를 포함하는 관계
//=> Container 와 Containee 의 라이프사이클이 간다
//=> 
//즉 Container (예:Order) 객체가 소멸될때 Containee 도 소멸된다.
public class Member {
    
    Address juso;
    
    public Member() {
        juso = new Address("111222","주소","zzz");
    }

}
