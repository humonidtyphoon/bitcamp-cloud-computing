// 모듈 정의 VI
// module 변수사용하기
//
//
//

var result = 0;

module.exports ={
		//{} 에서 함수를 정의하는 방법 1
		plus: function(value){result +=value},
		minus: function(value){result -=value},
		//{} 에서 함수를 정의하는 방법 2
		mutiple(value){result *=value},

		divide(value){result /=value},

		getResult(){return result}

};
