package CH07.Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class Deque_Lecture {
    public static void main(String[] args) {
        // 데크 예제 코드
        // Java 기본 제공 Deque 사용
        Deque deque = new ArrayDeque();

        // Front 부분 입력
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        System.out.println(deque); // front에서 rear방향으로 데이터를 넣기 때문에 [3, 2, 1] 출력

        // Rear 부분 입력
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);
        System.out.println(deque); // rear에서 front방향으로 데이터를 넣기 때문에 [3, 2, 1, 10, 20, 30] 출력

        // Front 부분 출력
        System.out.println(deque.removeFirst()); // 3 출력
        System.out.println(deque); // 3이 빠진 데크 [2, 1, 10, 20, 30] 출력

        // Rear 부분 출력
        System.out.println(deque.removeLast()); // 30 출력
        System.out.println(deque); // 30이 빠진 데크 [2, 1, 10, 20] 출력

        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast()); // rear쪽부터 순서대로 20, 10, 1, 2 빼서 출력
        System.out.println(deque); // 빈 데크 출력

        // poll vs remove
        System.out.println(deque.pollLast()); // 데크가 비어있을 때 예외가 아닌 null 리턴하여 출력
        System.out.println(deque.removeLast()); // 예외 발생하여 추후 예외처리가 필요
    }
}
