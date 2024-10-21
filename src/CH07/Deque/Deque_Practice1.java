package CH07.Deque;

// Practice1
// ArrayList를 이용한 데크 구현

import java.util.ArrayList;

class MyDeque1 {
    ArrayList list;

    MyDeque1() {
        this.list = new ArrayList<>();
    }

    public boolean isEmpty() {
        if(this.list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void addFirst(int data) {
        this.list.add(0, data);
    }

    public void addLast(int data) {
        this.list.add(data);
    }

    public Integer removeFirst() {
        if(this.isEmpty()) {
            System.out.println("Deque is empty");
            return null;
        }

        int data = (int)this.list.get(0);
        this.list.remove(0);
        return data;
    }

    public Integer removeLast() {
        if(this.isEmpty()) {
            System.out.println("Deque is empty");
            return null;
        }
        // list의 가장 마지막 인덱스 요소를 빼야하므로 get(리스트 크기 - 1)
        int data = (int) this.list.get(this.list.size()-1);
        this.list.remove(this.list.size()-1);
        return data;
    }

    public void printDeque() {
        System.out.println(list);
    }
}

public class Deque_Practice1 {
    public static void main(String[] args) {
        MyDeque1 deque1 = new MyDeque1();

        // Front 부분 입력
        deque1.addFirst(1);
        deque1.addFirst(2);
        deque1.addFirst(3);
        deque1.printDeque(); // front에서 rear방향으로 데이터를 넣기 때문에 [3, 2, 1] 출력

        // Rear 부분 입력
        deque1.addLast(10);
        deque1.addLast(20);
        deque1.addLast(30);
        deque1.printDeque(); // rear에서 front방향으로 데이터를 넣기 때문에 [3, 2, 1, 10, 20, 30] 출력

        // Front 부분 출력
        System.out.println(deque1.removeFirst()); // 3 출력
        deque1.printDeque(); // 3이 빠진 데크 [2, 1, 10, 20, 30] 출력

        // Rear 부분 출력
        System.out.println(deque1.removeLast()); // 30 출력
        deque1.printDeque(); // 30이 빠진 데크 [2, 1, 10, 20] 출력

        System.out.println(deque1.removeLast());
        System.out.println(deque1.removeLast());
        System.out.println(deque1.removeLast());
        System.out.println(deque1.removeLast()); // rear쪽부터 순서대로 20, 10, 1, 2 빼서 출력
        deque1.printDeque(); // 빈 데크 출력
    }
}
