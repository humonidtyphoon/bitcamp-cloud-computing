/**
 * Created with JetBrains PhpStorm.
 * User: oghx
 * Date: 11/29/12
 * Time: 2:51 AM
 */

  

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
var ggg= TicTacToe.gameBoard;
console.log("aaaaa",ggg);
console.log(TicTacToe.gameBoard+"==============================================")

TicTacToe.randomize = function(){
    return TicTacToe.gameBoard.random(TicTacToe.gameBoard.length);
};

/// 턴돌리기
TicTacToe.gameTurn = function(){

    TicTacToe.playerPlay(TicTacToe.getTarget());
    TicTacToe.winnerCheck();
    if(TicTacToe.hasWinner === false){
    	setTimeout(function(){ TicTacToe.comPlay();},300);
    }
    TicTacToe.winnerCheck();

};
// cpu 턴
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
//이벤트
TicTacToe.getTarget = function(){
    var target = event.target;
    return target;
};

TicTacToe.playerPlay = function(target){

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
        alert("너가 졌다");
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
        alert("너가 이김!");
        $("body").click(function() {
            location.reload();
        });
        TicTacToe.hasWinner = true;
    }else{
    }

};