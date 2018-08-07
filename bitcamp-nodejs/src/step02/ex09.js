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


  var mid ='user0001';
  // var mid ="user0001' or 1=1 or ''='";
  // SQL 문을 만들때 변수의 값을 직접 넣어서 만드는 경우
  // 해커의 공격에 노출될 수 있다.
  // 위에 주석을 막은 mid 변수의 값 처럼 외부 사용자가 값을 입력한다면
  // 조건이 무조건 참이 되기 때문에 전체 데이터가 삭제 되는 결과을 낳는다.
  // 그래서 SQL 문에 변수의 값을 직접 삽입하는ㄴ 방법을 써서는 안된다.

  // => in - parameter 방법을 사용하라!!
  con.query(
    `delete from pms2_member
      where mid ='${mid}'`,
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
