/**
 * Created with JetBrains PhpStorm.
 * User: oghx
 * Date: 11/29/12
 * Time: 2:51 AM
 */


  var Back = new Array(9);  // 게임판 만들기
  for( i =0;i<Back.length;i++){
      Back[i]=i;  // 게임판 생성
  }
  console.log("BB",Back.length);

  var game = document.createElement('div');
  game.id = "game";
  document.body.appendChild(game);  

   for(i=0;i<Back.length;i++){ // 카드뒷면 생성 
       Back[i]= document.createElement('div');
       Back[i].id="board"+i;
       Back[i].value=i;
       Back[i].setAttribute("onclick","TicTacToe.gameTurn()");
       // onclick 은 따로 없기 때문에 setAttribute 로 넣어 줘야 한다.
       
       game.appendChild(Back[i]);
      // game 이라는 div 에 종속 시키기
       
       Back.width=50;
       Back.height=50;
 
       Back.className =Back[i];
      
       
               
       Back.alt='';
   }
  

Array.prototype.random = function (length) {
    return this[Math.floor((Math.random()*length))][Math.floor((Math.random()*length))];
};

var TicTacToe = {};

TicTacToe.hasWinner = false;

//GameBoard Map for randomize set
TicTacToe.gameBoard = [
    ["box1", "box2", "box3"],
    ["box4", "box5", "box6"],
    ["box7", "box8", "box9"]
];
console.log(TicTacToe.gameBoard+"==============================================")

TicTacToe.randomize = function(){
    return TicTacToe.gameBoard.random(TicTacToe.gameBoard.length);
};


TicTacToe.gameTurn = function(){

    //Turn Mechanic
    TicTacToe.playerPlay(TicTacToe.getTarget());
    TicTacToe.winnerCheck();
    if(TicTacToe.hasWinner === false){
    	setTimeout(function(){ TicTacToe.comPlay();},300);
    }
    TicTacToe.winnerCheck();

};
TicTacToe.comPlay = function(){

    var computer = TicTacToe.randomize();
    if ($("#" + computer).html() != ""){
        do {
        	computer = TicTacToe.randomize();
        } while ($("#" + computer).html() != "")
        return $("#"+computer).html("O");
    }
    else{
        return $("#"+computer).html("O");
    }
};

TicTacToe.getTarget = function(){
    var target = event.target;
    return target;
};

TicTacToe.playerPlay = function(target){

    // Let the game begin!
    if (target.innerHTML === "X" || target.innerHTML === "O"){
        alert("You can not do this!");
        TicTacToe.playerPlay();
    }
    else{
        $('#'+target.id).html("X");
    }

};

TicTacToe.drawCheck = function(){
    var fullBoard;
    var boxes = 9;
    for (var i in TicTacToe.gameBoard){
        for(var j in TicTacToe.gameBoard){
            //console.log(TicTacToe.gameBoard[i][j][k]);
            if($("#" + TicTacToe.gameBoard[i][j]).html() != ""){
                boxes--;
            }
        }
    }
    if (boxes !== 0){
        console.log("해해해해해!");
    }
    else{
        alert("Draw!");
        $("body").click(function() {
            location.reload();
        });
    }

};

TicTacToe.winnerCheck = function(){

    //TicTacToe.drawCheck();

    if (
        //horizontal
        ($("#"+TicTacToe.gameBoard[2][0]).html() === "O") && ($("#"+TicTacToe.gameBoard[2][1]).html() === "O") && ($("#"+TicTacToe.gameBoard[2][2]).html() === "O") ||
        ($("#"+TicTacToe.gameBoard[0][0]).html() === "O") && ($("#"+TicTacToe.gameBoard[0][1]).html() === "O") && ($("#"+TicTacToe.gameBoard[0][2]).html() === "O") ||
        ($("#"+TicTacToe.gameBoard[1][0]).html() === "O") && ($("#"+TicTacToe.gameBoard[1][1]).html() === "O") && ($("#"+TicTacToe.gameBoard[1][2]).html() === "O") ||
        //vertical
        ($("#"+TicTacToe.gameBoard[0][0]).html() === "O") && ($("#"+TicTacToe.gameBoard[1][0]).html() === "O") && ($("#"+TicTacToe.gameBoard[2][0]).html() === "O") ||
        ($("#"+TicTacToe.gameBoard[0][1]).html() === "O") && ($("#"+TicTacToe.gameBoard[1][1]).html() === "O") && ($("#"+TicTacToe.gameBoard[2][1]).html() === "O") ||
        ($("#"+TicTacToe.gameBoard[0][2]).html() === "O") && ($("#"+TicTacToe.gameBoard[1][2]).html() === "O") && ($("#"+TicTacToe.gameBoard[2][2]).html() === "O") ||
        //diagonal
        ($("#"+TicTacToe.gameBoard[0][0]).html() === "O") && ($("#"+TicTacToe.gameBoard[1][1]).html() === "O") && ($("#"+TicTacToe.gameBoard[2][2]).html() === "O") ||
        ($("#"+TicTacToe.gameBoard[0][2]).html() === "O") && ($("#"+TicTacToe.gameBoard[1][1]).html() === "O") && ($("#"+TicTacToe.gameBoard[2][0]).html() === "O")
        ){
        alert("Computer Won!");
        $("body").click(function() {
            location.reload();
        });
        TicTacToe.hasWinner = true;
    }else if(
        //horizontal
        ($("#"+TicTacToe.gameBoard[0][0]).html() === "X") && ($("#"+TicTacToe.gameBoard[0][1]).html() === "X") && ($("#"+TicTacToe.gameBoard[0][2]).html() === "X") ||
        ($("#"+TicTacToe.gameBoard[1][0]).html() === "X") && ($("#"+TicTacToe.gameBoard[1][1]).html() === "X") && ($("#"+TicTacToe.gameBoard[1][2]).html() === "X") ||
        ($("#"+TicTacToe.gameBoard[2][0]).html() === "X") && ($("#"+TicTacToe.gameBoard[2][1]).html() === "X") && ($("#"+TicTacToe.gameBoard[2][2]).html() === "X") ||
        //vertical
        ($("#"+TicTacToe.gameBoard[0][0]).html() === "X") && ($("#"+TicTacToe.gameBoard[1][0]).html() === "X") && ($("#"+TicTacToe.gameBoard[2][0]).html() === "X") ||
        ($("#"+TicTacToe.gameBoard[0][1]).html() === "X") && ($("#"+TicTacToe.gameBoard[1][1]).html() === "X") && ($("#"+TicTacToe.gameBoard[2][1]).html() === "X") ||
        ($("#"+TicTacToe.gameBoard[0][2]).html() === "X") && ($("#"+TicTacToe.gameBoard[1][2]).html() === "X") && ($("#"+TicTacToe.gameBoard[2][2]).html() === "X") ||
        //diagonal
        ($("#"+TicTacToe.gameBoard[0][0]).html() === "X") && ($("#"+TicTacToe.gameBoard[1][1]).html() === "X") && ($("#"+TicTacToe.gameBoard[2][2]).html() === "X") ||
        ($("#"+TicTacToe.gameBoard[0][2]).html() === "X") && ($("#"+TicTacToe.gameBoard[1][1]).html() === "X") && ($("#"+TicTacToe.gameBoard[2][0]).html() === "X")
        ){
        alert("You Won!");
        $("body").click(function() {
            location.reload();
        });
        TicTacToe.hasWinner = true;
    }else{
        console.log("Go on!");
    }

};