// 주제: HTTP 서버만들기

// 1) 모듈 로딩

const http =require('http')

// 2) HTTP 서버 객체 생성
// => 파라미터는 : 요청이 들어 왔을떄 호출될 함수
const server = http.createServer((req,res)=>{
  console.log('요청 받았습니다.');

  //Web broser 에 요청을 받았지만 응답을 하지 않았기 있기때문에
  // web broser 는 계속 응답을 기다리는 상태에 있을 것이다.
});

// 3) HTTP 서버 생성
// => listener( 포트번호 , []서버가 시작된후 호출될 함수])
server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
