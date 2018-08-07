// 주제 : 모듈을 정의하고 사용하기 
//

// => 모듈명에서 ".js" 확장자를 생략해도 된다.
// => 모듈명으로 찾을 때 npm 이 설치한 모듈경로에서 찾기 때문에
//  소스경로에서 찾을수 없다.
var obj = require('./ex04_m.js')

console.log("=================")
console.log(obj.plus(10,20));
console.log(obj.minus(10,20));
console.log(obj.mutiple(10,20));
console.log(obj.divide(10,20));
