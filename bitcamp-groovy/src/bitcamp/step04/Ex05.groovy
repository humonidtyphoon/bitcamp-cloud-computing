package bitcamp.step04
// 메서드 - 클로저


//{} 를 이용하여 함수를 정의 할 수 이싿. 
plus = {
    int a, int b ->
    return a+b
}


result = plus 100, 200
println result
// 파라미터의 타입과 return 생략 가능
plus = {
     a, b ->
    a+b
}
result = plus 100, 200
println result

// 파라미터가 없으면  파라미터 선언부를 작성하지 않아도 된다.
hello = {println "hello"}
hello()

hello2 = {name -> println name +"님안녕"}
hello2"홍길동"