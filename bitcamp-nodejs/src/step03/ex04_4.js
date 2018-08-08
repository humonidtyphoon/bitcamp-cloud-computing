

// 주제:SQL 요청 처리하기 - 회원 삭제하기


// http:// localhost:8080/member/delete?memberId=user100
//[출력결과]
// 삭제 성공 입니다.
const http = require('http')
const url = require('url')
const mysql = require('mysql');

var pool = mysql.createPool({
  connectionLimit:10,
  host:'13.125.9.121',

  database:'studydb',
  user:'study',
  password:'1111'
});

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


  if(urlInfo.pathname !=='/member/delete'){
    res.end('해당 URL 지원XX!!')
    return;
  }
  var email=urlInfo.query.email

  var mid =urlInfo.query.mid

  pool.query(
    `delete from pms2_member
      where mid =?`,
      [mid], // ?(in 파라미터)개수 만큼 배열에 값을 담아 놓으면된다.
    function(err,results){
          if(err){
            res.end('DB 조회중.... 예외가 발생 했다.')
            return;
          }
            res.write(`${email},${mid}`)
            res.end('삭제완료');
  });
  //res.write(`${pageNo} ${pageSize}  ${startIndex}\n`)

});
server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
