package CH01.Stack;

import java.util.ArrayList;

// ArrayList를 이용한 모사 Stack 생성

class MyStack1 {
    ArrayList list;

    MyStack1() {
        this.list = new ArrayList();
    }

    public boolean isEmpty() {
        if(this.list.size() == 0) {
            return true;
        } else {
            return true;
        }
    }

    public void push(int data) {
        this.list.add(data); // add를 이용하여 가장 끝 부분에 데이터를 추가
    }

    public Integer pop() {
        if(this.isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        int data = (int) this.list.get(this.list.size() - 1); // get을 이용하여 가장 마지막(인덱스 : 리스트 사이즈-1)에 추가된 데이터를 꺼냄
        this.list.remove(this.list.size() - 1); // pop은 삭제 연산이므로 위에서 꺼낸 데이터를 삭제
        return data;
    }

    public Integer peek() {
        if(this.isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        int data = (int) this.list.get(this.list.size() - 1);
        return data;
    }

    public void printStack() {
        System.out.println(this.list);
    }
}

public class Stack_Practice1 {
    public static void main(String[] args) {
        MyStack1 myStack1 = new MyStack1();
        System.out.println(myStack1.isEmpty()); // true 출력 (스택이 빔)
        myStack1.push(1);
        myStack1.push(2);
        myStack1.push(3);
        myStack1.push(4);
        myStack1.push(5);
        myStack1.printStack(); // [1, 2, 3, 4, 5] 출력

        System.out.println(myStack1.peek()); // 5 출력
        myStack1.printStack(); // [1, 2, 3, 4, 5] 출력

        System.out.println(myStack1.pop()); // 5 빼냄
        System.out.println(myStack1.pop()); // 4 빼냄
        myStack1.printStack(); // [1, 2, 3]

        System.out.println(myStack1.pop()); // 3 빼냄
        System.out.println(myStack1.pop()); // 2 빼냄
        System.out.println(myStack1.pop()); // 1 빼냄
        myStack1.printStack(); // [] 출력 (빈 스택)
    }
}
