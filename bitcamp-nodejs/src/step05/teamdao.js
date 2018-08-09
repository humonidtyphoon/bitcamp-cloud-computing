// 주제: DAO 모듈 만들기

var pool;

exports.setConnectionPool = (connectionPool) => {
    pool = connectionPool;
};

exports.list = (pageNo = 1, pageSize = 3, handler) => {
    var startIndex = (pageNo - 1) * pageSize;

    pool.query('select name, sdt,edt,max_qty from pms2_team limit ?, ?',
            [startIndex, pageSize],
            function(err, results) {
        handler(err, results);
    });
};
