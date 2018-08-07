// 주제 : 데이터베이스 프로그래밍 -DB 연결하기



const mysql = require('mysql');


//  1) DBMS 와 연결을 수행할 객체를 준비한다.
var con = mysql.createConnection({
  host:'13.125.9.121', // host 가 local 이면 생략가능
  //port:'3306',  // 포트 번호가 3306 이면 생략가능
  database:'studydb',
  user:'study',
  password:'1111'
});


console.log(mysql);
console.log(con);
//  2) 연결 객체를 통해 DBMS 와 연결한다.
//    =>connect 연결완료후 호출될 함수
con.connect(function(err){
  // 만약 연결에 실패 했다면, err 파라미터값이 존재할 것이다.
  if(err)throw err; //예외를 던저 오류가 있음을 알려라

  console.log('연결성공');

});
//  3) DBMS 와의 연결 해제를 예약한다.
//    =>즉 다음 코드를 실행하는 것은
//      connect() 를 통해 함수 호출이 끝나면
//      예약 작업이 실행될 것이다.
//      즉 연결 해제하라고 예약한 작업이 실행될것이다.
//      서버와의 연결을 끊으라고 예약하는 것이다.,
//    => end DBMS 와 연결을 끊은후 호출될 함수!!
con.end(function(err){
  if(err)throw err; // 만약 연결을 끊는다면
  console.log('연결을 끊었습니다.');
}); // 지금 당장 연결을 끊으라는 거은 아니다.
console.log('연결테스트!!');
