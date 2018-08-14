package bitcamp.uml.classdiagram.ex02;

import java.util.List;

//Aggregation (집합)
// = > 한 객체가 다른 객체를 포함하는 것을 표현한다.
// = > 포함하는 객체를 Container 라 부르고,
//     포함 되는 객체를 Containee 라 부른다.
//  => Container 와 Containee 의 라이프사이클이 다르다.
//     즉 Container (예:Order) 객체가 소멸되더라도
//     Containee( 예 product) 소멸되더라고 존한다. 
public class Order {
    // 포함할 객체는 필드로 선언한다.
    Customer customer;
    
    // 여러개를 포함할 경우 배열이나 Collection 객체로 구현한다. 
    List<Product> products;
    
}
