// 주제: 클라이언트 에게 출력하기 - URL 에 따라 작업을 분리하기
const http = require('http')

const url = require('url')

const server = http.createServer((req,res)=>{
  var urlInfo = url.parse(req.url,true);
  console.log("요청 받았습니다.");
  if(urlInfo.pathname === '/favicon.ico'){
    res.end();
    return;
  }

  res.writeHead(200,{
    'Content-Type':'text/plain;charset=UTF8'
  });

  if(urlInfo.pathname ==='/board/list'){
    res.write('게시물목록!!')

  }else if(urlInfo.pathname === '/board/add'){
    res.write('게시물등록')

  }else{
    res.write('해당 url을 지원하지 않습니다.')
  }

  res.end();

});

server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
