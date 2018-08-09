// 주제:Templete engine 용
// =>

const express = require('express');

const app = express();

//템플릿 엔진 모듈을 로딩한다.
const handlebars = require('handlebars')

//템플릿 소스를 정의한다.
var TempleteSrc='{{name}}님 안녕하세요!';

// 템플릿 소스에 데이터를 삽입하여 최종 결과를 만들어낼 함수를 준비한다.
var TempleteFn = handlebars.compile(TempleteSrc);

app.get('/hello',(req,res)=>{
  res.writeHead(200,{'Content-Type': 'text/plain;charset=UTF-8'});

  // 템플릿 함수를 호출하여 소스로부터 결과를 얻는다.
  // 소스에 삽입될 데이터를 파라미터로 넘긴다.
  var resultStr =TempleteFn({name:'홍길동'});
  res.write(resultStr)
  res.end();
});
//  express 끝
app.listen(8000,()=>{
  console.log('서버 실행 중 ....!!')
})
