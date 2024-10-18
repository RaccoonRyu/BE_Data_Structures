package CH02.Queue;

// Array를 통한 모사 Queue 작성 (원형 큐)
// 원형 큐 : 배열로 큐를 구현했을 때, 여러 개의 데이터를 Enqueue 후에 Dequeue 하면 배열 앞 인덱스에 빈 공간이 생겨 더 이상 Enqueue할 수 없는 상황을 해결하기 위해 원형으로 만든 Queue

class MyQueue2 {
    int[] arr;
    int front = 0;
    int rear = 0;

    MyQueue2(int size) {
        arr = new int[size+1]; // front가 가리키는 부분은 항상 비워두어야 하므로 데이터 크기 + 1
    }

    public boolean isEmpty() {
        if(this.rear == this.front) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        // rear가 가리키는 부분이 원형 큐의 가장 마지막일 때 -> (rear + 1) % 배열의 길이 == front
        return (this.rear + 1) % (this.arr.length) == this.front;
    }

    public void enQueue(int data) {
       if (this.isFull()) {
           System.out.println("Queue is Full!");
           return;
       }
        // 데이터를 rear쪽에서 추가하므로 rear 1 증가 -> rear가 배열의 끝에 도달하면 원형으로 돌아야 하기 때문에 나머지 연산
       this.rear = (this.rear + 1) % this.arr.length;
       this.arr[this.rear] = data;
    }

    public Integer deQueue() {
        if (this.isEmpty()) {
            System.out.println("Queue is Empty!");
            return null;
        }
        // 데이터를 front쪽에서 뺄 때 처음 front가 가리키는 공간에는 데이터가 없으므로 front 1 증가 -> front 또한 원형으로 돌아야 하기 때문에 나머지 연산
        front = (this.front + 1) % this.arr.length;
        return this.arr[front];
    }

    public void printQueue() {
        // 큐의 시작 지점 유의 - 실질적으로 데이터가 존재하는 시작점이므로 front 1 증가 및 원형으로 돌아야 하기 때문에 나머지 연산
        int start = (this.front + 1) % this.arr.length;
        // 큐의 종료 지점 유의 - 실질적으로 데이터가 존재하는 종료점이므로 rear 1 증가 및 원형으로 돌아야 하기 때문에 나머지 연산
        int end = (this.rear + 1) % this.arr.length;

        for (int i = start; i != end ; i = (i + 1) % this.arr.length) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();

    }
}

public class Queue_Practice2 {
    public static void main(String[] args) {
        MyQueue2 myQueue2 = new MyQueue2(5);
        System.out.println(myQueue2.isEmpty()); // true 출력 (스택이 빔)
        myQueue2.enQueue(1);
        myQueue2.enQueue(2);
        myQueue2.enQueue(3);
        myQueue2.enQueue(4);
        myQueue2.enQueue(5);
        myQueue2.enQueue(6); // Queue is Full 출력
        myQueue2.printQueue(); // [1, 2, 3, 4, 5] 출력

        System.out.println(myQueue2.deQueue()); // 1 빼냄
        System.out.println(myQueue2.deQueue()); // 2 빼냄
        myQueue2.printQueue(); // [3, 4, 5]

        myQueue2.enQueue(6);
        myQueue2.enQueue(7);
        myQueue2.printQueue(); // [3, 4, 5, 6, 7 출력]

        System.out.println(myQueue2.deQueue()); // 3 빼냄
        System.out.println(myQueue2.deQueue()); // 4 빼냄
        System.out.println(myQueue2.deQueue()); // 5 빼냄
        myQueue2.printQueue(); // [6, 7] 출력
        System.out.println(myQueue2.deQueue()); // 6 빼냄
        System.out.println(myQueue2.deQueue()); // 7 빼냄
        myQueue2.deQueue(); // Queue is Empty!
    }
}
