// 주제: Node.js 소개 

// Node.js
// - 구글에서 공개한 V8 자바스크립트 엔진을 사용하여 명령창에서
// 자바스크립트를 실행할 수 있도록 만든 프로그램이다./
// 자바스크립트 API 를 그대로 사용할 수 있다.
// 웹 브라우저에서 실행하는 것이 아니기 때문에
// 웹 브라우저에서 제공하는 Window history location naviagtor
// screen document 은 사용 불가 
// 
// Node.js 데스크톱의 자원을 접근할 수 있도록별도의 라이브러리를 제공한다.
// 주로 마이크로 서비스를 만들때 Node.js 를 사용함으로 인해
// node.js 가 서버 프로그램 용으로 오해하는 경우가 많다.
//
//
//

// 사용법
// > node 파일명
// > node ex01.js
// > node src/step01/ex01.js
// > 
console.log("hello");
/*
console.log(window);  //error
console.log(history); //error
console.log(location); //error
console.log(naviagtor); //error
console.log(screen);  //error

*/