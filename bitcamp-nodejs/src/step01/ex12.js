// 주제 :Node.js 제공하는 객체(빌트인객체)

//node가 실행하는 소스파일의 디렉토리 알아내기
//console.log(global.__dirname); 글로벌에 담겨있지 않음
console.log(__dirname);

//디렉토리 및 파일 이름까지 포함
console.log(__filename);

// 'path' 모듈을 이용하면 더 다양하게 경로를 다룰수 있다
// => 보통 모듈을 실행한 후에 값을 리턴 받는 변수는 모듈명과 같은 모듈이름으로 짓는다.


var path = require('path');
// 경로를 결합
//path join ('경로1',경로2','경로3'.....)
// => 여러개의 경로를 붙여 한 경로로 만들어 리턴하낟.
// = > 각 경로 앞에 /를 붙이지 않아도 된다.
console.log(path.join('c:/apps','/aaa','bbb','okok.js'));

// 기존 파일의 경로를 기준으로 다른 파일의 경로를 설정하기
console.log(path.join(__dirname,'ex12_m.js'));

// 물론 경로에서 .또는 ..을 사용할 수 있다.
console.log(path.join(__dirname,'../step02/ex12_m.js'));
