// 모듈 정의 II
//  module.exports - 객체를 사용하하는 대신에
// 새 객체를 만들어 리턴할수 있다


module.exports ={
		//{} 에서 함수를 정의하는 방법 1
		plus: function(a,b){return a+b},
		minus: function(a,b){return a-b},
		//{} 에서 함수를 정의하는 방법 2
		multiple(a,b){return a*b},
		divide(a,b){return a/b}
};
