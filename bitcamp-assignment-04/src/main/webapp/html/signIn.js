'use strict'
$('#loginBtn').click(() => {
	

	
    $.post(`${serverApiAddr}/json/auth/signIn`, {
        'email': $('#fEmail').val(),
        'password': $('#fPassword').val(),
        'saveEmail':$('#fSaveEmail').prop('checked')
    }, (result) => {
        console.log(result);
    }, 'json')
    .fail(() => {
        alert('로그인 중에 오류 발생!')
    });
});


console.log('===========>');