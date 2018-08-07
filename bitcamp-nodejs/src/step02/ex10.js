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


//  var mid ='user0001';
  var mid ="user0001' or 1=1 or ''='";
// => in - parameter 방법을 사용하라!!
  con.query(
    `delete from pms2_member
      where mid =?`,
      [mid], // ?(in 파라미터)개수 만큼 배열에 값을 담아 놓으면된다.
      function(err,result){
              if(err)throw err;
              console.log('삭제 성');
            });

  con.end(function(err){
    if(err)throw err;
    console.log('연결을 끊었습니다.');
  });
  console.log('연결테스트!');
  console.log('delete 실행!!');
