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

// 커넥션 풀 객체를 이용해 바로 질의를 ㅅ행한다.
// => pool.getConnection() -> con.query () -> con.release() 의 단축 사용법
//This is a shortcut for the pool.getConnection()
// -> connection.query() -> connection.release() code flow.
pool.query('select*from pms2_member',function(err,results){
    if(err)throw err;
    //console.log('결과를 가져왔다',results);
    for(var row of results){
        // row 객체에서 값을 꺼낼때는 SQL  지정한 컬럼명 또는 별명을 사용한다.
        console.log('데이터',row.email,row.mid,row.pwd);
      }

      // 이예제에서는 sql 수행한 후에 프로그램을 종료해야 하기 때문에
      // 커넥션 풀을 닫는다.
      pool.end();
});
//커넥션 풀에 있는 모든 커넥션을 닫으라고 예약한다.
console.log("select 실행!!!");
//ex11.js & ex12.js 차이점
//////////////////////
//ex12.js  는 커넥션 풀에 대해 바로 query 를 호출 할 수 있어
// 코드가 간결해 져서 좋다.
// but query 를 호출 할 떄마다 새 커넥션을 만들어 (꺼내 ) 사용하기 떄무넹
// 한번에 여러개의 SQL 을 실행 할 떄 불리 하다.
//  왜? 같은 커넥션을 사용하지 않기 때문이다.
//==> ex))
// pool.query(...)//  커넥션1
// pool.query(...) // 커넥션2
// [ex11.js]
// => SQL 실행 할때마다 매번 커넥션을 얻어야 하기 때문에
//    ex12.js -> 보다는 코드가 복잡하다.
//    그래도 한번 커넥션을 얻으면 어려번 사용할 수 있기때문에
//    여러개의 SQL 을 실행할  때 ex12.js 보다 는 좋다.
//==> ex))
//     pool.getConnection(function (err,con){
//        con.query(...);커넥션1
//        con.query(...);커넥션2
//        con.query(...);커넥션3
//        })
