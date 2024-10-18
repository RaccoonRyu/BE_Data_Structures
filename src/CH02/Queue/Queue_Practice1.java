package CH02.Queue;

// ArrayList를 통한 모사 Queue 작성

import java.util.ArrayList;

class MyQueue1 {
    ArrayList list;

    MyQueue1() {
        this.list = new ArrayList();
    }

    public boolean isEmpty() {
        if(this.list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void enQueue(int data) {
        this.list.add(data);
    }

    public Integer deQueue() {
        if(this.isEmpty()) {
            System.out.println("Queue is Empty!");
            return null;
        }
        // 가장 처음에 들어온 데이터를 삭제해야 하므로 index는 0
        int data = (int) this.list.get(0);
        this.list.remove(0);
        return data;
    }

    public Integer peek() {
        if(this.isEmpty()) {
            System.out.println("Queue is Empty!");
            return null;
        }
        // 가장 처음에 들어온 데이터를 꺼내야하므로 index는 0
        int data = (int) this.list.get(0);
        return data;
    }

    public void printQueue() {
        System.out.println(this.list);
    }
}

public class Queue_Practice1 {
    public static void main(String[] args) {
        MyQueue1 myQueue1 = new MyQueue1();
        System.out.println(myQueue1.isEmpty()); // true 출력 (스택이 빔)
        myQueue1.enQueue(1);
        myQueue1.enQueue(2);
        myQueue1.enQueue(3);
        myQueue1.enQueue(4);
        myQueue1.enQueue(5);
        myQueue1.printQueue(); // [1, 2, 3, 4, 5] 출력

        System.out.println(myQueue1.peek()); // 1 출력
        myQueue1.printQueue(); // [1, 2, 3, 4, 5] 출력

        System.out.println(myQueue1.deQueue()); // 1 빼냄
        System.out.println(myQueue1.deQueue()); // 2 빼냄
        myQueue1.printQueue(); // [3, 4, 5]

        System.out.println(myQueue1.deQueue()); // 3 빼냄
        System.out.println(myQueue1.deQueue()); // 4 빼냄
        System.out.println(myQueue1.deQueue()); // 5 빼냄
        myQueue1.printQueue(); // [] 출력 (빈 스택)
    }
}
