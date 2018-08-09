// 주제: express 사용하기 - 정적HTML 파일을 서비스하기

const express = require('express')
const app =express();
//Posrt 요청 데이터 처리
const static = require('serve-static');
const bodyParser = require('body-parser')
const path = require('path');
app.use(bodyParser.urlencoded({extended: false}))

//정적 html
//app.use(express.static('public'))
app.use(static(path.join(__dirname, 'public')));

app.get('/test01',(req,res)=>{
  res.writeHead(200, {'Content-Type':'text/plain;charset=UTF-8'});
  res.write(`name=${req.query.name}\n`);
  res.write(`age=${req.query.age}\n`);

  res.end();
})
//POST 요청
app.post('/test02',(req,res)=>{
  res.writeHead(200, {'Content-Type':'text/plain;charset=UTF-8'});
  res.write(`name=${req.body.name}\n`);
  res.write(`age=${req.body.age}\n`);

  res.end();
})

// 서버 실행 !!
app.listen(8000, () => {
    console.log('서버 실행 중...!!!');
});
