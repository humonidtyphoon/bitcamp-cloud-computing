// 주제:  dao 모듈 만들기
//
//
var pool;
exports.setConnectionPool=(connectionPool)=>{
  pool = connectionPool;
};
exports.list = (pageNo=1,pageSize=3,handler)=>{
  var startIndex = (pageNo - 1) * pageSize;

  //this 생략 불가
  pool.query('select mid, email from pms2_member limit ?, ?',
          [startIndex, pageSize],
          function(err, results) {
        handler(err,results);
  });
}

exports.add = (data,handler)=>{
  pool.query(
          'insert into pms2_member(mid,email,pwd)\
          values(?, ?, password(?))',
      [req.query.id, req.query.email, req.query.password],
      function(err, result) {
          handler(err,result)

  });
};

exports.update = (data,handler)=>{
    pool.query(
            'update pms2_member set\
             email=?,\
             pwd=?\
             where mid=?',
        [req.query.email,
         req.query.password,
         req.query.id],
        function(err, result) {
          handler(err,result);
        });
      };
exports.remove = (data,handler)=>{
  pool.query('delete from pms2_member where mid=?',
      [req.query.id],
      function(err, result) {
        handler(err,result)
      });
    };
