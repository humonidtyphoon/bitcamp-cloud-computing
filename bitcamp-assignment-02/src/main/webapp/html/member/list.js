"use strict"


var trTemplateSrc= $('#tr-template-src').text();
var trTemplateFn = Handlebars.compile(trTemplateSrc);

var {page, size} = $.parseQuery(location.href);

let tbody = $('#eListTable > tbody');
let data = null;

if (page != undefined && size != undefined) {
    loadList(page, size);
} else {
    loadList(1, 15);
}


function loadList(page, size) {
    $.getJSON(serverApiAddr + '/json/member/list',
        {
            page: page,
            size: size
        }, function() {console.log("로딩 성공!")})
     .done(function(result) {
       data = result;
       
       var trListHTML = trTemplateFn({list: data.list});
       tbody.html(trListHTML);


    });
}
///

var data1 = null;
var {id, page, size} = $.parseQuery(location.href);
console.log(`$.parseQuery(location.href`);
$.getJSON(serverApiAddr + `/json/member/view/${id}`, 
	    function(result) {
			$('#eUpdateBtn').css('display', 'none');
			//$('#eAddBtn').css('display', 'none');
	        data1 = result;
	       /*
	        if (data1.member == null) {
	            alert('이메일 혹은 비밀번호가 틀립니다.');
	            location.href = "login.html";
	            return;
	        }
	        */
	    console.log(id);
	    console.log(data1)
	    
	     if(id ==undefined){
	    	 $('.viewform').css('display', 'none');
	    	 $('#eDeleteBtn').css('display', 'none');
		   	  $(eId).removeAttr("readonly");
		   	  $(eEmail).removeAttr("readonly");
			  $(eFax).removeAttr("readonly");
			  $(ePhone).removeAttr("readonly");
			  $(eIPhone).removeAttr("readonly");
			  $(eContent).removeAttr("readonly");
	    	 
				$('#eAddBtn').click(function() {
					 $.post(serverApiAddr + '/json/member/add2', 
				        {
				            id: $(eId).val(), 
				            email: $(eEmail).val(), 
				            phone: $(ePhone).val(), 
				            iPhone: $(eIPhone).val(),
				            fax: $(eFax).val(), 
				            content: $(eContent).val()
				            
				        },
				        function(data) {
				            if (data.status == 'success') {
				                location.href = `list.html?id=${id}&page=${page}&size=${size}`;
				            } else {
				                alert('변경 오류입니다!');
				                console.log(data.error);
				            }
				        },
				        'json');
				    
				    
				});
	     }else{
	        $(eId).val(data1.member.id);
	        $(eEmail).val(data1.member.email);
	        $(eFax).val(data1.member.fax);
	        $(ePhone).val(data1.member.phone);
	        $(eIPhone).val(data1.member.iphone);
	        $(eContent).val(data1.member.content);
	     }
	});

// 이 방식은 실행 시점에 존재하는 태그에 대해서만 이벤트 핸들러를 등록할 수 있다.

$('.viewLink').click(function (event) {
    event.preventDefault();
    var id = $(event.currentTarget).attr('data-id');
    location.href = `list.html?id=${id}&page=${data.page}&size=${data.size}`;
});


$(eDeleteBtn).click(function() {
    $.getJSON(serverApiAddr + `/json/member/delete?id=${eId.value}`, 
        function(data) {
            if (data.status == 'success') {
                location.href = `list.html?page=${page}&size=${size}`;
            } else {
                alert('삭제 오류입니다!');
                console.log(data.error);
            }
    });
});


$(eModify).click(function() {
	
	  $('#eAddBtn').css('display', 'none');
	  $('#eDeleteBtn').css('display', 'none');
	 
	  $(eId).removeAttr("readonly");
	  $(eFax).removeAttr("readonly");
	  $(ePhone).removeAttr("readonly");
	  $(eIPhone).removeAttr("readonly");
	  $(eContent).removeAttr("readonly");
	 
	  
	  $('#eUpdateBtn').css('display', 'inline');
	  
	
		$(eUpdateBtn).click(function() {
		    $.post(serverApiAddr + '/json/member/update', 
		        {
		            id: $(eId).val(), 
		            email: $(eEmail).val(), 
		            phone: $(ePhone).val(), 
		            iPhone: $(eIPhone).val(),
		            fax: $(eFax).val(), 
		            content: $(eContent).val()
		            
		        },
		        function(data) {
		            if (data.status == 'success') {
		                location.href = `list.html?id=${id}&page=${page}&size=${size}`;
		            } else {
		                alert('변경 오류입니다!');
		                console.log(data.error);
		            }
		        },
		        'json');
		    
		    
		});
	
		  
});




$('#eAddBtn01').click(function() {
	
	  $('#eAddBtn01').css('display', 'none');
	  $('#eDeleteBtn').css('display', 'none');

	 
	  
	
	  
	
	
	
		  
});
/*
$(eUpdateBtn).click(function() {
    $.post(serverApiAddr + '/json/member/update', 
        {
            id: $(eId).val(), 
            email: $(eEmail).val(), 
            fax: $(eFax).val(), 
            phone: $(ePhone).val(), 
            iPhone: $(eIPhone).val(),
            content: $(eContent).val(),
            password: $(ePassword).val()
        },
        function(data) {
            if (data.status == 'success') {
                location.href = `list.html?page=${page}&size=${size}`;
            } else {
                alert('변경 오류입니다!');
                console.log(data.error);
            }
        },
        'json');
});

*/
// 실행 시점에 존재하지 않더라도 이벤트 핸들러를 등록하는 방법은?
// => 앞으로 생성될 태그의 부모에 리스너를 등록하는 것이다.
tbody.on('click', 'a.viewLink', function(event) {
    event.preventDefault();
    var id = $(event.target).attr('data-id');
    location.href = `list.html?id=${id}&page=${data.page}&size=${data.size}`;
});
