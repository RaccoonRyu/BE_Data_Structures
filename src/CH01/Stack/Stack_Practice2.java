package CH01.Stack;

import java.util.ArrayList;

// Array를 이용한 모사 Stack 생성

class MyStack2 {
    int[] arr;
    int top = -1; // 마지막으로 데이터가 들어온 위치. 아무 데이터도 없으므로 -1로 초기화

    MyStack2(int size) {
        arr = new int[size];
    }

    public boolean isEmpty() {
        // top을 이용하여 구현 (스택에 아무 데이터가 없으면 -1인 점에 기인)
        if(this.top == -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        // 배열은 사이즈가 고정이다보니 가득 찬 여부를 확인해야함
        if(this.top == this.arr.length - 1) { // top과 array의 길이 -1이 같은 경우 스택이 가득 참
            return true;
        } else {
            return false;
        }
    }

    public void push(int data) {
        if(this.isFull()) {
            System.out.println("Stack is Full!");
            return;
        }

        this.top += 1; // 스택이 꽉 차지 않았으면 top 증가
        this.arr[this.top] = data; // top 위치에 데이터 추가
    }

    public Integer pop() {
        if(this.isEmpty()) {
            System.out.println("Stack is Empty!");
            return null;
        }

        int data = this.arr[this.top]; // array 내 top 위치(가장 마지막에 들어온) 데이터 꺼냄
        this.top -= 1; // top 1 감소 (마지막 데이터의 자리 뺴줌 - 데이터를 삭제한 것이 아니므로 데이터는 남지만 top을 통해 접근할 수 없음)
        return data;
    }

    public Integer peek() {
        if(this.isEmpty()) {
            System.out.println("Stack is Empty!");
            return null;
        }

        int data = this.arr[this.top]; // array 내 top 위치(가장 마지막에 들어온) 데이터 꺼냄
        return data; // 데이터 빼지 않고 바로 리턴
    }

    public void printStack() {
        for (int i = 0; i < top + 1; i++) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }
}

public class Stack_Practice2 {
    public static void main(String[] args) {
        MyStack2 myStack2 = new MyStack2(5); // size 5로 스택 생성
        System.out.println(myStack2.isEmpty()); // true 출력 (스택이 빔)
        myStack2.push(1);
        myStack2.push(2);
        myStack2.push(3);
        myStack2.push(4);
        myStack2.push(5);
        myStack2.push(6); // 스택 꽉참 출력
        myStack2.printStack(); // [1, 2, 3, 4, 5] 출력

        System.out.println(myStack2.peek()); // 5 출력
        myStack2.printStack(); // [1, 2, 3, 4, 5] 출력

        System.out.println(myStack2.pop()); // 5 빼냄
        System.out.println(myStack2.pop()); // 4 빼냄
        myStack2.printStack(); // [1, 2, 3]

        System.out.println(myStack2.pop()); // 3 빼냄
        System.out.println(myStack2.pop()); // 2 빼냄
        System.out.println(myStack2.pop()); // 1 빼냄
        myStack2.printStack(); // [] 출력 (빈 스택)
    }
}
