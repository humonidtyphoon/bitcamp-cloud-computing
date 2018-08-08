// 주제: 클라이언트 에게 출력하기 -quiz: 계산기를 만들라
// http://localhost:8000/calc?a=100&b=200&op=%2b
// http://localhost:8000/calc?a=100&b=200&op=-
// http://localhost:8000/calc?a=100&b=200&op=*
// http://localhost:8000/calc?a=100&b=200&op=/

const http = require('http')

const url = require('url')

const server = http.createServer((req,res)=>{
  console.log("요청 받았습니다.");


  res.writeHead(200,{
    'Content-Type':'text/plain;charset=UTF8'
  });

  var urlInfo = url.parse(req.url,true);
  var a =parseInt(urlInfo.query.a);
  var b =parseInt(urlInfo.query.b);
  var op= urlInfo.query.op;
  var result =0;

  switch (op) {
    case '+':result = a + b; break;
    case '-':result = a - b; break;
    case '*':result = a * b; break;
    case '/':result = a / b;; break;

    default:


  }
  res.write(`${a}${op}${b}=${result}`)
  res.end();

});

server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
