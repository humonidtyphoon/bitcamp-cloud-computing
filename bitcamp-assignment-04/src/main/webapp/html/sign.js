'use strict'

$('#addBtn').click(() => {
    $.post(`${serverApiAddr}/json/member/signUp`, {
        'email': $('#fEmail').val(),
        'name': $('#fName').val(),
        'password': $('#fPassword').val()
    }, (result) => {
    	
    	if(result.status ==='success'){
    		console.log("성공")
    		location.href='signIn.html'
    	}else{
    		alert('실패');
    		console.log(result.message)
    	}
        console.log(result);
    }, 'json')
    .fail(() => {
        alert('회원 가입 중에 오류 발생!')
    });
});


