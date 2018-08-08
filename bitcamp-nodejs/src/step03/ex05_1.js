

// 주제:SQL 여러개의 요청 처리하기 - 회원 목록/ 등록/ 변경 / 삭제하기 한파일에


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


  if(urlInfo.pathname !=='/member/list'){
    res.end('해당 URL 지원XX!!')
    return;
  }


  var pageNo =1;
  var PageSize =3;

  if(urlInfo.query.pageNo){
    pageNo =parseInt(urlInfo.query.pageNo);
  }
  if(urlInfo.query.pageSize){
    pageSize =parseInt(urlInfo.query.pageSize);
  }

  var startIndex = (pageNo -1) * pageSize;

  pool.query('select*from pms2_member limit ?,?',
    [startIndex,pageSize],
    function(err,results){
          if(err){
            res.end('DB 조회중.... 예외가 발생 했다.')
            return;
          }
          for(var row of results){

              res.write(`${row.email},${row.mid},${row.pwd}\n`);
            }
            res.end();
  });
  //res.write(`${pageNo} ${pageSize}  ${startIndex}\n`)

});
server.listen(8000,()=>{
  console.log('서버 시작 !!!');
});
