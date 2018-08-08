// 주제: HTTP 서버만들기 - single Thread
// -> NOode js  single thread  로 동작한다.
// => 즉 !!! 한 클라이언트의 요청을 처리 할 때 까지 다른 클라이언트는 기다려야한다.
// => 그래서 Node.js HTTP 서버는 시간이 오래걸리는
//    대량 동시 요청을 처리하기에 적합하지 않다.
//    그런용도가 아니다.
// =>  작업시간이 짧은 단타성 요청을 처리하기에 적합하다.


const http =require('http')


const server = http.createServer((req,res)=>{
  console.log("요청 받았습니다.");

  //클라이언트 요청이들어오면 10초 후에 응답해보자!!
  //= > 테스트 방법 : 웹 브라우저 탭을 두개 띄운 다ㅇ믕에
  // 이서버에 접속해봐라
  setTimeout(()=>{
    res.end();
  },10000)
});

server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
