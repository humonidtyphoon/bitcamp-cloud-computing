// 주제: 클라이언트 에게 출력하기 - 멀티 라인

const http =require('http')


const server = http.createServer((req,res)=>{
  console.log("요청 받았습니다.");

  //출력하는 콘텐트의 타입을 설정해야 한글이 깨지지 않는다.
  // 응답 헤더로 'Content-type'  을 지정하라!
  res.writeHead(200,{
    'Content-Type':'text/html;charset=UTF8'
  });
  // write 를 사용하여 콘텐트를 출력할수 있다.
res.write('<html>\n\
<head>\n\
<title>NODE JS</title>\n\
</head>\n\
<body>\n\
<h1>안녕!!</h1>\n\
</body>\n\
</html>\n');


    //end 에서 꼭 출력할 필요 없다.
  res.end();

});

server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
