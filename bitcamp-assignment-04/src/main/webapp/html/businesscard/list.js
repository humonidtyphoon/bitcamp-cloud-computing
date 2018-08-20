'use strict'

var liTemplateSrc= $('#tr-template-src').text();

var template = Handlebars.compile(liTemplateSrc);


$.getJSON(`${serverApiAddr}/json/businesscard/list`,(result)=>{
	
	var html = template(result);
	$('#name-list').html(html);

	
})
/*
	$('#name-list').on('click','li',function(){
		alert('okok');
		
		console.log(this);
		console.log($(this).attr('data-no'));
	})
	*/
		$('#name-list').on('click','li',(e)=>{
		//alert('okok');
		
		//console.log(this);
		var no =$(e.target).attr('data-no');
		$(document.body).trigger('show.detail',[no]);
	});
		

