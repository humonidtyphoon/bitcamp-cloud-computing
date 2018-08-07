// 주제 : 모듈을 정의하고 사용하기 
//

// => exports 에 저당된 함수 받기


var obj = require('./ex06_m')();


console.log(obj.plus(100,20));
console.log(obj.minus(100,20));