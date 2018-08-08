// 주제: 클라이언트 에게 출력하기 - 요청 파라미터 값 꺼내기
const http = require('http')

const url = require('url')

const server = http.createServer((req,res)=>{
  console.log("요청 받았습니다.");


  res.writeHead(200,{
    'Content-Type':'text/plain;charset=UTF8'
  });



  //query 객체에서 파라미터 명을 사용하여 꺼내고 싶다면
  // 두번쨰 파라미터의 값을 true 로 설정하라!/
  // => parse()함수가 파라미터값을 꺼내기 쉽도록
  // query 객체 프로
  var urlInfo = url.parse(req.url,true);




  // 파라미터 값을 꺼낼때는 그냥
  /// " query".파라미터명 으로 지정한다
  res.write(`name=${urlInfo.query.name}\n`)
  res.write(`age=${urlInfo.query.age}\n`)
  res.end();

});

server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
