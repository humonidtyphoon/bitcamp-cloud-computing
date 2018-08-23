package bitcamp.step01;


// Java에서 Groovy 클래스 사용

// -> groovy 소스를 컴파일하면 자바 클래스에 대한 바이트 코드가 생성된다.
// 즉 자바에서 그루비의 클래스를 그대로 사용할 수 있다.
// 
public class Ex03 {
    public static void main(String[] args) {
       // Groovy 클래스 사용  호환이 된다
       Ex02 obj = new Ex02();
       System.out.println("결과"+obj.plus(100,200));
    }
}
