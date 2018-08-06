// 주제 : 모듈을 정의하고 사용하기  II
//
// 리턴값 destructuring
// require 리턴한 exports객체를 분해해서 받을수 있다.

var {plus,minus} = require('./ex03_m.js')

console.log("=================")
console.log(plus(10,20));
console.log(minus(10,20));
