// 주제: express 사용하기 - URL 경로에 따라 모듈로 핸들러를  요청핸들러를 모듈로 분리하기


const express = require('express')
const app =express();
//Posrt 요청 데이터 처리
//const static = require('serve-static');
const bodyParser = require('body-parser')
const path = require('path');
app.use(bodyParser.urlencoded({extended: false}))

//정적 html
//app.use(express.static('public'))
//app.use(static(path.join(__dirname, 'public')));

app.use(express.static('public'))

// 통합 템플릿 엔진 관리자 모듈 로딩
// => 템플릿 엔진이 아니라 템플릿 엔진을 중간에서 관리해주는 역할을 수핸한다

const consolidate =require('consolidate');

// Express 템플릿 엔진을 등록한다,
// => consolidate 에 대해 handlebars를 지정하면
//    이템플릿 관리자는 Node 모듈에서 handlebars 를 찾아 리턴한다.
// =>  여러개의 Express 여러개의 엔진을 등록할수 있다.
app.engine('html',consolidate.handlebars)

//  등록된 템플릿 엔진중에 사용할 엔진을 지정한다.
app.set('view engine','html')
// 템플릿 파일이 있는 디렉토리 경로를 지정한다.

app.set('views',path.join(__dirname,'templates'))

// /member/*" URL 을 처리할 라이터와 "/team/*" URL 을 처리할 라우터를 로딩한다.
var memberRouter = require('./member')
var teamRouter = require('./team')

// -> 라우터를 Express 의 웹 서버에 등록한다.

app.use('/member',memberRouter)
app.use('/team',teamRouter)






app.get('/test03',(req,res)=>{
  res.render('template01',req.query)
})


// 서버 실행 !!
app.listen(8000, () => {
    console.log('서버 실행 중...!!!');
});
