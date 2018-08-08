// 주제: 클라이언트 에게 출력하기 - 요청 정보 다루기

const http =require('http')


const server = http.createServer((req,res)=>{
  console.log("요청 받았습니다.");
  // 클라이언트가 요청한 url 을 보기
  console.log(req.url);
  res.writeHead(200,{
    'Content-Type':'plain/html;charset=UTF8'
  });
  // write 를 사용하여 콘텐트를 출력할수 있다.
  res.write('테스트');
    //end 에서 꼭 출력할 필요 없다.
  res.end();

});

server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
