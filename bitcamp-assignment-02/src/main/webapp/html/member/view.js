"use strict"

var data = null;
var {id, page, size} = $.parseQuery(location.href);

$(eAddBtn).click(function() {
    $.post(serverApiAddr + '/json/member/add', 
        {
            id: $(eId).val(), 
            email: $(eEmail).val(), 
            password: $(ePassword).val()
        },
        function(data) {
            location.href = 'login.html';
        },
        'json');
});
    