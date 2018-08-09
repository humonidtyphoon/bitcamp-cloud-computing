// 주제: express 사용하기 - GET/POST Paramter 처리

// express 모듈 로딩하기
const express = require('express')
// => express 객체 만들기
//= > 웹 서버 객체 준비
const app =express();

//=> URL 에 대해 함수를 연결하기
app.get('/test01',(req,res)=>{
  res.writeHead(200, {'Content-Type':'text/plain;charset=UTF-8'});
  res.write(`name=${req.query.name}\n`);
  res.write(`age=${req.query.age}\n`);

  res.end();
})

// 서버 실행 !!
app.listen(8000, () => {
    console.log('서버 실행 중...!!!');
});
