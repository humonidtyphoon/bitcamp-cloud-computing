// 주제 : 데이터베이스 프로그래밍 -connection pool 사용!!
//  connect
//     |
//     |---sql실행
//     |
//     |---end 예약



const mysql = require('mysql');

var pool = mysql.createPool({
  connectionLimit:10,
  host:'13.125.9.121',

  database:'studydb',
  user:'study',
  password:'1111'
});

pool.getConnection(function(err,con){
  if(err) throw err;
  console.log("연결객체를 얻었다.");
  con.query('select*from pms2_member',function(err,results){
    if(err)throw err;
    //console.log('결과를 가져왔다',results);
    for(var row of results){
      // row 객체에서 값을 꺼낼때는 SQL  지정한 컬럼명 또는 별명을 사용한다.
      console.log('데이터',row.email,row.mid,row.pwd);
    }

    con.release(); // 컨넥션 풀에 연결객체를 반납

    // 프로그램을 종료하고 싶다면 작업이 끝난 후 커넥션 풀의 모든 커넥션을 닫아야 한다.
    // 보통 서버로서 실행하다가 종료할 때 커넥션 풀을 닫는다.
    // 커넥션 풀을 닫지 않으면 Node js 의 메인 쓰레드가 종료 되지 않느다.
    // 물론 서버로 실행 할 때에는 당연히 그래야하지만
    // 이 프로그램 예제처럼 그냥 간단히 사용하고 종료 하고 싶다면
    // 다음과 같이 질의를 완료한 후에 커넥션 풀을 닫ㅇ라 !!
    // => 이런 단일 프로그램은 커넥션 풀을 사용할 필요가 없다.
    // 서버에서 사용할 것을 대비해 만든 예제 이기 때문에
    // 질의가 끝난 후 닫는 것 이다.
    // 일반적으로 이렇게 프로그램 하지 않는다.
    pool.end(function (err) {
      console.log('커넥션 풀에 있는 모든 커넥션을 닫습니다.!!!');
    });
  });
});

//커넥션 풀에 있는 모든 커넥션을 닫으라고 예약한다.
console.log("select 실행!!!");
