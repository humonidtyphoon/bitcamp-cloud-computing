// 주제: 클라이언트 에게 출력하기 - POST 요청 다루기
const http = require('http')
const url = require('url')
const server = http.createServer((req,res)=>{
  var urlInfo = url.parse(req.url,true);
  console.log("요청 받았습니다.");

  res.writeHead(200,{
    'Content-Type':'text/html;charset=UTF8'
  });
  if(urlInfo.pathname !=="/"){
    res.end();
    return;
  }
  if(req.method !=='POST'){
    res.end('<h1>GET 요청을 지원하지 않습니다.</h1>')
    return;
  }

  //POST 요청 데이터 처리 !!

  //  1) data 이벤트 처리
  //  => 클라이언트로 보낸 데이터를 일부 읽을 때마다 이벤트가 발생한다
  //  =>!!!주의!!!!
  //    클라이언트가 보낸 데이터를 모두 읽었을 때 발생하는 이벤트가 아니다.
  //    즉 !! 클라이언트가 본내 데이터를 한번에 모두 읽는게 아니라
  //    일부 읽을 때마다 알려주는 방식으로 동작한다.
  //  => 따라서 !! 데이터를 일부 읽었으면 'data '이벤트가 발생하고,
  //    그 이벤트에 등록된 함수가 있다면 호출해준다.
  //  => listener(이벤트 핸들러) 에 넘어오는 파라미터는 클라이언트가 보낸
  //    데이터를 일부이다.

  var data='';
  req.on('data',(chunk)=>{
    data += chunk;
    //res.write(data);
  })
  req.on('end',()=>{
    res.write(data);
    res.end();
  });

});

server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
