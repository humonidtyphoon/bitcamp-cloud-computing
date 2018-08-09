// 주제:DAO 분리
// =>
const mysql = require('mysql');
const express = require('express');

const router = express.Router();
const teamdao = require('./teamdao')


var pool = mysql.createPool({
    connectionLimit: 10,
    host: '13.125.9.121',
    //port: '3306',
    database: 'studydb',
    user: 'study',
    password: '1111'
});
teamdao.setConnectionPool(pool);
// 커넥션 풀 넘기기!!!

router.use( (req,res, next)=> {
  console.log('team.js실행');
  next();
})


//get 요청에 대해 핸들러를 등록하기
router.get('/list',(req,res)=>{
  res.writeHead(200,{'Content-Type': 'text/plain;charset=UTF-8'});
  var pageNo = 1;
  var pageSize = 3;

  if (req.query.pageNo) {
      pageNo = parseInt(req.query.pageNo)
      console.log("eroor1");
  }
  if (req.query.pageSize) {
      pageSize = parseInt(req.query.pageSize)
        console.log("eroor2");
  }
    console.log("eroor3");
  teamdao.list(pageNo,pageSize,(err,results)=>{
      if (err) {
        console.log(err);
        res.end('DB 조회 중 예외 발생!')
        return;
      }
      for (var row of results) {
        console.log("11ero?");
        res.write(`팀이름:${row.name}, 시작일:${row.sdt},종강일:${row.edt},최대인원:${row.max_qty}\n`);
      }
      res.end();
  });

});//list 끝

//  express 끝
module.exports = router;
