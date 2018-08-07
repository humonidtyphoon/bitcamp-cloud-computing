// 주제 : 모듈을 정의하고 사용하기
//

// => exports 에 저당된 함수 받기


var fn = require('./ex07_m')

console.log("=================")
//클로저만 접근할수있는 변수이기에 객체마다 고유하다
var obj1 =fn();
var obj2 =fn();


// obj1 에 들어있는 클로저가 사용하는 result 변수와
// obj2 에 들어있는 클로저가 사용하는 result 변수와 다르다.


obj1.plus(100);
obj1.minus(7);

obj2.plus(100);
obj2.divide(7);
// result 변수는 리턴된 객체에 들어 있지 않ㄷ.
// plus () minus() 등 클로저가 공유하는 메모리에 들어 이싿.
// 그래서 외부에서 직접 접근할수 없다.
// 자바 문법으로 따지면 일종의 private



//클로저가 공유하는 변수의 값을 알고 싶다면.,
// 그 변수의 값을 꺼내는 클로저를 호출해야한다.
/// 일조의 자바의 getter 라고 볼수 있다.

console.log(obj1.getResult());
console.log(obj2.getResult());
