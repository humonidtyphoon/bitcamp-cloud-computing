

// 주제:SQL 요청 처리하기 - 회원 변경


// http:// localhost:8080/member/update?memberId=user100&email=user100@test.com&pwd=1111
//[출력결과]
// 변경 성공
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


  if(urlInfo.pathname !=='/member/update'){
    res.end('해당 URL 지원XX!!')
    return;
  }





  var email=urlInfo.query.email
  var mid =urlInfo.query.mid
//  var pwd =urlInfo.query.pwd



  pool.query(
      `update pms2_member set email=? where mid =?`,
        [email,mid],
    function(err,results){
          if(err){
            res.end('DB 조회중.... 예외가 발생 했다.')
            return;
          }
          res.write(`${email},${mid}`)
          res.end('회원업데이트 완료');
  });
  //res.write(`${pageNo} ${pageSize}  ${startIndex}\n`)

});
server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
