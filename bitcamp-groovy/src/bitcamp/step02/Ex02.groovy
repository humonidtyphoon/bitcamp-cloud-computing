package bitcamp.step02

/**
 동적 타입 바인딩
 
 def a = 20;
 a ="문자열"
 b="

*/

//정적 타입 변수 :  자바 문법과 같다.
def a =10
println a

// -> 타입이 지정되지않았기 때문에 다른 타입의 값을 저장 할 수 있다.
a= "Hello";

println a
// 부동 소수점 값이 그대로 변수의 저장
a=3.14
println a

// 원리
// = > 타입을 지정하지 않으면 Object 타입의 변수로 선언되다.


