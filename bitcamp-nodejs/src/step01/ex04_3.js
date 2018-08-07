// 주제 : 모듈을 정의하고 사용하기 
//
// 만약 함수를 단 한번만 호출할 것이라면, 

var fn = require('./ex04_m.js').plus;

console.log("=================")
console.log(fn(10,20));
