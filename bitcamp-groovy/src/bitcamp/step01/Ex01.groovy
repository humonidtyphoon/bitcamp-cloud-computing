// groovy- 컴파일 과 실행



package bitcamp.step01
// 컴파일 : 그루비 소스 파일을 컴파일하여 바이트 코드를 생성
// = > groovy  -> .class


// 그루비 컴파일러가 자동으로 생성하는 클래스 
// -> groovy.lang.Script 를 상속 받는다.
// 
// 클래스 나 메서드 안에 작성하지 않고 다음과 같이 바깥에 작성한 코드는
// 자동으로 생성되는 클래스의 run () 메서드 안에 들어간다.
// => 생성자 메서드와 main() 메서드를 자동생성한다.
// run()
println "hello"