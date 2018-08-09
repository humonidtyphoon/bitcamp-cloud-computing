// 주제: /team/* 요청을 처리할 라우터 만들기



const express = require('express')

const router = express.Router();

router.use( (req,res, next)=> {
  console.log('team.js실행');
  next();
})

///team/list 요청이 왔을떄
router.get('/list',(req,res)=>{
  res.writeHead(200, {'Content-Type':'text/plain;charset=UTF-8'});
  res.write('회원 목록입니다.');

  res.end();
})
///team/view 요청이 왔을떄
router.get('/view',(req,res)=>{
  res.writeHead(200, {'Content-Type':'text/plain;charset=UTF-8'});
  res.write('회원상세 보기 페이지 입니다.');
  res.end();
})


//module 생략하면 안된다.
module.exports = router;
