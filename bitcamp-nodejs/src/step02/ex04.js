// 주제 : 데이터베이스 프로그래밍 -select 실행


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

//query(sql , 실행 후 호출될 함수)
// => sql 을 바로 실행하는 것이 아니라 실행을 예약한다.
// => DBMS 와 연결 되면 이렇게 예약한 SQL 문을 실행할 것이다.
// => 문제는 연결 오류에 상관없이 무조건 예약한다는 것이다.
//    connect() 등록한 함수에서 예외를 던지지 않ㅇ면
//    SQl 문을 실행한다.
con.query('select*from pms2_member',function(err,results){
  if(err)throw err;

  //console.log('결과를 가져왔다',results);
  for(var row of results){
    // row 객체에서 값을 꺼낼때는 SQL  지정한 컬럼명 또는 별명을 사용한다.
    console.log('데이터',row.email,row.mid,row.pwd);
  }
});


con.end(function(err){
  if(err)throw err;
  console.log('연결을 끊었습니다.');
});
console.log('연결테스트!');
console.log('select 실행!!');
