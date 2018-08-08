// 주제: 클라이언트 에게 출력하기 - URL 분석하기

const http = require('http')

const url = require('url')

const server = http.createServer((req,res)=>{
  console.log("요청 받았습니다.");
  // 클라이언트가 요청한 url 을 보기
  //console.log(req.url);

  res.writeHead(200,{
    'Content-Type':'text/plain;charset=UTF8'
  });

  res.write(req.url+'\n');
  // URL 분석기를 가지고 URL을 분석해보자 !!
  var urlInfo = url.parse(req.url);

  res.write(`href=${urlInfo.href}\n`);
  res.write(`pathname=${urlInfo.pathname}\n`);
  res.write(`search=${urlInfo.search}\n`);
  res.write(`query=${urlInfo.query}\n`);

  // write 를 사용하여 콘텐트를 출력할수 있다.

    //end 에서 꼭 출력할 필요 없다.
  res.end();

});

server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
