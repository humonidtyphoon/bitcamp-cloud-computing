// 주제 : 데이터베이스 프로그래밍 -select 실행 II
//  connect
//     |
//     |---sql실행
//     |
//     |---end 예약

const mysql = require('mysql');

var con = mysql.createConnection({
  host:'13.125.9.121',

  database:'studydb',
  user:'study',
  password:'1111'
});

console.log(mysql);
console.log(con);

con.connect(function(err){

  if(err)throw err;

  console.log('연결성공');
});

  var title='user0001@test.com';
  var content ='user0001';
  con.query(
    `insert into pms2_board(titl,cont,cdt) values(?,?,now()))`,
            [title,content],
            function(err,result){
              if(err)throw err;
              console.log(result);
            });

  con.end(function(err){
    if(err)throw err;
    console.log('연결을 끊었습니다.');
  });
  console.log('연결테스트!');
  console.log('insert 실행!!');
