package bitcamp.uml.classdiagram.ex05;


// Inheritance (상속)

// => 다른 클래스를 상속할 때의 관계
// 
public class Teacher extends Member{
    public Teacher(String email, String name, String password) {
        super(email, name, password);
        // TODO Auto-generated constructor stub
    }

    private int payPerHour;
}
