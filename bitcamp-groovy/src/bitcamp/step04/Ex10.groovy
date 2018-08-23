package bitcamp.step04
// 메서드 - 파라미터로 클록저 넘기기 IV

def name = "익명";

printHello = {
    env ->
    if(env != null) {
        env()
    }
        println name+"님 반갑습니다."
    
    
}

printHello
printHello{
 name ="홍길동"   
}
