// 모듈 정의 V
//  private 변수를 갖는 객체 만들기
//
//
//
module.exports =function(){

	var result = 0;

	return{

		//{} 에서 함수를 정의하는 방법 1
		plus: function(value){result +=value},
		minus: function(value){result -=value},
		//{} 에서 함수를 정의하는 방법 2
		multiple(value){result *=value},

		divide(value){result /=value},

		getResult(){return result}
	};
};
