package bitcamp.step03
// 반복문

def a=10;

while(a>0) {
    print a+",";
    a--
}
println "\n============"


for( int i =0;i<10;i++) {
    print i+","
}
println "\n============"

for(i in 1..10) {
    print i+","
}
println "\n============"

for(name in ["홍길동","유관순","임꺽정"])
    print name +","
 println "\n============"
 
 for(entry in ["홍길동":20,"유관순":10,"임꺽정":10])
     println entry.key+ "="+entry.value
     
 println "\n============"
 
 for(c in "ABC가각간123")
      print c+","
 println "\n======================="