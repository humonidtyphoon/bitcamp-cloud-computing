// 주제: 클라이언트 에게 출력하기


const http =require('http')


const server = http.createServer((req,res)=>{
  console.log("요청 받았습니다.");

  // 출력하는 데이터의 콘텐틍 타입을 웹 브라우저에게 알려주지 않았기 떄문에
  // web broser 는 제대로 출력하지 못한다, 한글 꺠진다.
  res.end('안녕!!');

});

server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
