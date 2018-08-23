package bitcamp.step04
// 메서드 - 클로저 it 배열


// it 배열은 자바스크립트의 arguments와 같다
plus = {
    return it[0] + it[1]
}


result = plus ([100, 200])
println result
