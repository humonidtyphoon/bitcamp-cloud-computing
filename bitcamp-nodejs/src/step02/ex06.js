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
  // 연결에 성공 했을 때만 실행하라고 예약한다.
  con.query('select*from pms2_member',function(err,results){
    if(err)throw err;
    //console.log('결과를 가져왔다',results);
    for(var row of results){
      // row 객체에서 값을 꺼낼때는 SQL  지정한 컬럼명 또는 별명을 사용한다.
      console.log('데이터',row.email,row.mid,row.pwd);
    }
  });

  // end 또한 sql 종료 후에 예약해야 한다. 
  con.end(function(err){
    if(err)throw err;
    console.log('연결을 끊었습니다.');
  });
  console.log('연결테스트!');
  console.log('select 실행!!');
});
