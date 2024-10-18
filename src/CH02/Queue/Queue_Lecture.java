package CH02.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class Queue_Lecture {
    public static void main(String[] args) {
        // 큐 예제 코드
        // 1. Java에서 Queue를 쉽게 사용할 수 있는 방법 Queue 클래스
        // Java에서 Queue는 인터페이스로 작성되어 있으므로, 바로 객체를 생성할 수 없다.
        // 따라서 Queue에 필요한 연산들이 구현되어있는 LinkedList()를 사용한다. (다형성)
        Queue queue = new LinkedList();

        // add(Enqueue) : 데이터 추가
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        System.out.println("queue = " + queue); // [1, 2, 3, 4, 5] 출력

        // poll(Dequeue) : 데이터 꺼냄
        System.out.println(queue.poll()); // 가장 먼저 들어간 데이터인 1 꺼내고 출력
        System.out.println("queue = " + queue); // [2, 3, 4, 5] 출력

        System.out.println(queue.poll()); // 2 꺼내고 출력
        System.out.println("queue = " + queue); // [3, 4, 5] 출력

        // peek : 큐에 가장 먼저 들어온 데이터를 반환하고 꺼내지는 않음
        System.out.println(queue.peek());
        System.out.println("queue = " + queue); // [3, 4, 5] 출력

        // contains(값) : 매개변수로 들어온 값이 큐에 존재하는지 여부 검사
        System.out.println(queue.contains(3)); // true 출력

        // size() : 큐의 크기 반환
        System.out.println(queue.size()); // 3 출력 (3, 4, 5 총 3개)

        // isEmpty() : 큐가 비어있는지 여부 검사
        System.out.println(queue.isEmpty()); // false 출력 (3, 4, 5 총 3개 데이터 있으므로)

        // clear() : 큐의 모든 데이터 삭제
        queue.clear();
        System.out.println("queue = " + queue); // 비어있는 큐 [] 출력
        System.out.println(queue.poll()); // poll은 stack의 pop과 달리 예외를 발생시키지 않고 null 출력
    }
}
