package bitcamp.step02

scoreMap =["kor":100,"eng":90,"math":100,"sc":90]

// 맵 생성하기

println scoreMap["kor"]
println scoreMap.eng
println scoreMap.get("sc")

//println scoreMap1.getClass()


//맵에 값 추가하기

scoreMap1["art"]="100"
scoreMap1.Fix="200"
scoreMap1.put("xxx","200")

println scoreMap1

// 빈 맵 만들기
map2 = [:]
println map2