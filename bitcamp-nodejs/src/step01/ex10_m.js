// 모듈 정의 VIII
// module변수와 글로벌 변수
//
//
//

var v1 = 100;

console.log("ex10_m",v1);

// 글로별 변수는 모든 모듈이 공유한다.
console.log("global ",global.v1);

//모듈에서 글로벌 변수의 값을 바꾸기

global.v1 =300;
