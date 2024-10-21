package CH07.Deque;

// Practice1
// ArrayList를 이용한 데크 구현

import java.util.ArrayList;

class MyDeque2 {
    // deque 관리를 위한 배열과 front/rear 변수 생성
    int[] arr;
    int front = 0;
    int rear = 0;

    MyDeque2(int size)  {
        // 데크는 저번 Queue처럼 원형으로 작성하기 때문에 front가 가리키는 자리를 비워두기 위해 size + 1
        this.arr = new int[size + 1];
    }

    public boolean isEmpty() {
        // rear와 front가 같은 위치를 가리키면 empty
        if(this.front == this.rear) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        return (this.rear + 1) % this.arr.length == this.front;
    }

    public void addFirst(int data) {
        if(this.isFull()) {
            System.out.println("Deque is Full!");
            return;
        }
        // front에 자료를 넣으면 다음에 front 방향으로 정보를 넣을 때 방향을 유지하기 위해 데크를 회전시켜야 함
        // ex) 0번 인덱스에 자료를 넣은 후 front 방향으로 정보를 넣으면 방향을 유지하기 위해 회전하여 4번 인덱스에 자료가 들어가야 함
        // 따라서 front에서 1을 빼고 회전을 위해 배열의 길이를 더한 다음 배열의 길이로 나머지 연산하면 회전하여 front 방향 다음 인덱스에 접근할 수 있다.
        // ex) 인덱스 0에 자료 넣음 => 0 - 1 + 5 % 5 = 4번 인덱스
        this.arr[front] = data;
        this.front = (this.front - 1 + this.arr.length) % this.arr.length;
    }

    public void addLast(int data) {
        if(this.isFull()) {
            System.out.println("Deque is Full!");
            return;
        }
        // 리어 조정 먼저
        this.rear = (this.rear + 1) % this.arr.length;
        this.arr[this.rear] = data;
    }

    public Integer removeFirst() {
        if(isEmpty()) {
            System.out.println("Deque is Empty!");
            return null;
        }
        // 기본적으로 front가 가리키고있는 공간에는 데이터가 없으므로 그 다음 부분의 데이터를 remove
        this.front = (this.front + 1) % this.arr.length;
        return this.arr[this.front];
    }

    public Integer removeLast() {
        if(isEmpty()) {
            System.out.println("Deque is Empty!");
            return null;
        }

        int data = this.arr[this.rear];
        // 리어 조정 - 조정할 때 deque 안쪽으로 움직이기 때문에 -1하고 배열의 길이 더한 후 배열의 길이로 나머지 연산
        this.rear = (this.rear -1 + this.arr.length) % this.arr.length;
        return data;
    }

    public void printDeque() {
        int start = (this.front + 1) % this.arr.length;
        int end = (this.rear + 1) % this.arr.length;

        for (int i = start; i != end; i = (i + 1) %this.arr.length) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }
}

public class Deque_Practice2 {
    public static void main(String[] args) {
        MyDeque2 deque1 = new MyDeque2(5);

        // Front 부분 입력
        deque1.addFirst(1);
        deque1.addFirst(2);
        deque1.addFirst(3);
        deque1.printDeque(); // front에서 rear방향으로 데이터를 넣기 때문에 [3, 2, 1] 출력

        // Rear 부분 입력
        deque1.addLast(10);
        deque1.addLast(20);
        deque1.addLast(30); // Deque is Full!
        deque1.printDeque(); // rear에서 front방향으로 데이터를 넣기 때문에 [3, 2, 1, 10, 20] 출력

        // Front 부분 출력
        System.out.println(deque1.removeFirst()); // 3 출력
        deque1.printDeque(); // 3이 빠진 데크 [2, 1, 10, 20, 30] 출력

        // Rear 부분 출력
        System.out.println(deque1.removeLast()); // 20 출력
        deque1.printDeque(); // 30이 빠진 데크 [2, 1, 10] 출력

        System.out.println(deque1.removeLast());
        System.out.println(deque1.removeLast());
        System.out.println(deque1.removeLast()); // rear쪽부터 순서대로 10, 1, 2 빼서 출력
        System.out.println(deque1.removeLast()); // removeLast() 했지만 deque1에 데이터가 없어서 return null되어 Deque is Empry와 null 출력

    }
}
