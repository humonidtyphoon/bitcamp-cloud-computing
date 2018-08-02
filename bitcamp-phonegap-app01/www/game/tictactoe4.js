/*
 * jquery를 이용한 attr 
 */


var user = 'X';
var cpu ='O';

$('.cell').click((e) => {
	
	console.log($(e.target).attr('data-pos')+
	'cell.....clicked');
	$(e.target).text(user);
	//클릭시 x-> 바꾸기 TEXT
	
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