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

});
  // 이예제에서는 연결완료후 END 를 먼저 실행하라고 예약 했기때문에
  // connect() 등록한 함수가 호출됭어 SQL 을 예약하더라도
  // end 보다 그 이우헤 실행되기 했기때문에
  // SQL 실행  오류 방생
  //
  // 해결책 ? sql 실행을 먼저 예약한다음에 end를 실행하라  --> ex06.js
  con.end(function(err){
    if(err)throw err;
    console.log('연결을 끊었습니다.');
  });
  console.log('연결테스트!');
  console.log('select 실행!!');
