/**
 * TicTacToe 게임
 */
 var player = 'X';
 var cpu = "O";
 var currentTurn = player;

    function startGame() {

        for (var i = 1; i <= 9; i++) {
            clearBoard(i);
        }
        document.winner = null;
        setMessage("Player " + currentTurn + ", 게임을 시작하십시오.");
    }

    function setMessage(message) {
        document.getElementById("message").innerText = message;
    }   

    function nextMove(game) {
        if (document.winner != null) {
            setMessage(currentTurn + " 가 이미 이겼습니다.");
        } else if(game.innerText == '') {
        	game.innerText = currentTurn;
          swapTurn();       
        } else {
            setMessage("이미 입력된 칸입니다.");
        }
    }
    
    function swapTurn() {
        if(checkForWinner(currentTurn)) {
            setMessage(currentTurn + " 승리");
            document.winner = currentTurn; 
        } else if(checkForDraw(currentTurn)) {
            setMessage("무승부!");
        } else if(currentTurn == player){
            currentTurn = cpu;
            setMessage("Player " + currentTurn + ", it's your turn.");
            cpuMove();
        } else {
            currentTurn = player;
            setMessage("Player " + currentTurn + ", it's your turn.");
        }
    }
 // 이길 경우의 수
    function checkForWinner(move) {
        var result = false;
        if(checkRow(1,2,3, move) ||
           checkRow(4,5,6, move) ||
           checkRow(7,8,9, move) ||
           checkRow(1,4,7, move) ||
           checkRow(2,5,8, move) ||
           checkRow(3,6,9, move) ||
           checkRow(1,5,9, move) ||
           checkRow(3,5,7, move)) {
            result = true;
           }
           return result;
    }

    function checkForDraw(move) {
        var draw = false;
        if (((getBox(1) == "X") || (getBox(1) == "O")) && 
            ((getBox(2) == "X") || (getBox(2) == "O")) && 
            ((getBox(3) == "X") || (getBox(3) == "O")) && 
            ((getBox(4) == "X") || (getBox(4) == "O")) && 
            ((getBox(5) == "X") || (getBox(5) == "O")) && 
            ((getBox(6) == "X") || (getBox(6) == "O")) && 
            ((getBox(7) == "X") || (getBox(7) == "O")) && 
            ((getBox(8) == "X") || (getBox(8) == "O")) && 
            ((getBox(9) == "X") || (getBox(9) == "O"))) {
             draw = true;
            }
             return draw;  
    }

    function checkRow(a, b, c, move) {
        var result = false;
        if(getBox(a) == move && getBox(b) == move && getBox(c) == move) {
            result = true;
        }
        return result;
    }

    var data = document.querySelectorAll("g");
    console.log("sss",data);
    function getBox(number) {                                   
        return document.getElementById("g" + number).innerText; 
    }


    function clearBoard(number) {
        document.getElementById("g" + number).innerText = "";
    }

    function cpuMove() {

        var blanks = [];
        for (var i = 1; i <= 9; i++) {
          var place = document.getElementById("g"+i).innerText
          if(place == "") blanks.push([i]);
        }
        if (blanks.length > 0) {
          var r = Math.floor((Math.random() * blanks.length));
          setTimeout(function (){nextMove(document.getElementById("g"+blanks[r]))},1000);
        } 
      }