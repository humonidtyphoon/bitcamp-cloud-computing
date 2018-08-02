/*
 * jquery를 이용한 attr 
 * 		var num = Math.floor(Math.random()*9);
		$('.cell:nth-child()').each((i,e) =>{
			//console.log(i,e);
			var el=
			console.log($(e).text());
			$(e.target).text(cpu);
		});
		
 */

var cells = $('.cell');
var user = 'X';
var cpu ='O';
var count =0;
$('.cell').click((e) => {
	
	console.log($(e.target).attr('data-pos')+
	'cell.....clicked');
	$(e.target).text(user);
	//클릭시 x-> 바꾸기 TEXT
	
	
	// 카운트 5라면
	if(++count == 5) return;
	// 마지막 표시를 했으면 컴퓨터가 더이상 작업 XX
	
	// 타이머 가동하여 1초 후에 컴퓨터가 표시하게 한다.
	setTimeout(() =>{
		while(true){
		var num = Math.floor(Math.random()*9);
		console.log("====문자열확인!!=====")
		//console.log(num,getCellText(num))
		//console.log(num,$(`.cell:nth-child(${num+1})`).text());
		//if($(`.cell:nth-child(${num+1})`).text()==""){
		//	$(e.target).text(cpu);
		//}
//		if(cells[num].innerHTML ==""){
//			cells[num].innerHTML = cpu;
//			return ;
//			}
		if(!isCellChecked(num)){
			checkCell(num,'cpu');
			return
			}
		}
		console.log("computer turn")
		
	},500);
	
});

/*
 * 
 */
//new game
$('#new-game').click((e) =>{
	console.log('new game!!!');
});


function isCellChecked(num){
	return cells[num].innerHTML !="" ? true : false;
}

function checkCell(num,user){
	 cells[num].innerHTML =
		(user =='cpu')? cpu : user;
}
