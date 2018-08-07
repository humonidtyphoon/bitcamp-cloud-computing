// 주제 : 모듈을 정의하고 사용하기
//

// => exports 에 저당된 함수 받기


var obj1 = require('./ex08_m')
var obj2= require('./ex08_m')

console.log("=================")
obj1.plus(100);  //result 100
obj1.minus(7);  //result 93

obj2.plus(100);//result 193
obj2.mutiple(7); //result 193/3

console.log(obj1.getResult());
console.log(obj2.getResult());


//= = > require () 가 리턴하는 객체의 함수는
//  같은 모듈 변수를 사용한다.
