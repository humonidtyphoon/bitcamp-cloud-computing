// 주제: 클라이언트 에게 출력하기 - content type 지정하기

const http =require('http')


const server = http.createServer((req,res)=>{
  console.log("요청 받았습니다.");

  //출력하는 콘텐트의 타입을 설정해야 한글이 깨지지 않는다.
  // 응답 헤더로 'Content-type'  을 지정하라!
  res.writeHead(200,{
    'Content-Type':'text/plain;charset=UTF8'
  });
  res.end('안녕!!');

});

server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
