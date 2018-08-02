/*
 * jquery를 이용한 attr 
 */
$('.cell').click((e) => {
	
	/*console.log(e.target.getAttribute('data-pos')+
			'cell.....clicked');
			*/
	//가공 jquery로 바꾸기VVVVVVVVVVV
	console.log($(e.target).attr('data-pos')+
	'cell.....clicked');
	
});

/*
 * 
 */
//new game
$('#new-game').click((e) =>{
	console.log('new game!!!');
});

/*
 * 
 */