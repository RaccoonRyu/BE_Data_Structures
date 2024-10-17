package CH01.Stack;

import java.util.Stack;

public class Stack_Lecture {
    public static void main(String[] args) {
        // 스택 예제 코드
        // 1. Java에서 Stack을 쉽게 사용할 수 있는 방법 Stack 클래스
        Stack stack1 = new Stack();

        // Stack의 연산(push, pop 등)은 Stack 클래스 안에 메서드로 정의되어있음
        // push
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack1.push(4);
        stack1.push(5);
        System.out.println("stack1 = " + stack1); // [1, 2, 3, 4, 5] 출력

        // pop
        stack1.pop();
        System.out.println("stack1 = " + stack1); // [1, 2, 3, 4] 출력 (가장 마지막에 들어온 5가 꺼내짐)

        stack1.pop();
        System.out.println("stack1 = " + stack1); // [1, 2, 3] 출력 (뒤에서 두번째로 들어온 4가 꺼내짐)

        // pop은 수행 시 꺼내지는 데이터를 리턴해주므로 꺼내지는 데이터의 출력은 왼쪽과 같이 수행
        System.out.println(stack1.pop()); // 3 꺼내지며 리턴
        System.out.println("stack1 = " + stack1); // [1, 2] 출력
        stack1.push(3);

        // peek
        // 스택의 가장 마지막 데이터를 반환하나 스택에서 꺼내지는 않는 연산
        System.out.println(stack1.peek());
        System.out.println(stack1);

        // contains
        // 스택에 매개변수로 넣은 값이 존재하는지 검사하는 연산
        System.out.println(stack1.contains(1)); // 스택에 1이라는 데이터가 있으므로 true 출력

        // size
        // 스택의 크기를 반환하는 연산
        System.out.println(stack1.size()); // 현재 스택의 크기가 3이므로 3 출력

        // empty
        // 스택이 비어있는지 여부를 검사
        System.out.println(stack1.empty()); // 현재 스택이 비어있지 않으므로 false 출력

        // clear
        // 스택에 있는 모든 데이터 삭제
        stack1.clear();
        System.out.println(stack1); // 스택이 비워짐을 확인. [] 출력
        stack1.pop(); // 이후 비어있는 스택에 pop 하는 경우 에러 발생 - EmptyStackException

    }
}
