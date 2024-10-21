package CH01.Stack.Practices;

// Practice2
// 괄호 짝 검사

// 입출력 예시
// 입력 : "("
// 출력 : Fail

// 입력 : ")"
// 출력 : Fail

// 입력 : "()"
// 출력 : Pass

// Key : "("가 들어올 때 push하고 ")"가 들어올 때 pop하여 스택의 empty 여부를 확인한다.
// 스택이 empty면 짝이 맞으므로, 여는 괄호 = 닫는 괄호일 때 Pass이다. (여는 괄호보다 닫는 괄호가 적거나 많으면 안됨!)

import java.util.Stack;

public class St_Practice2 {
    // 괄호 짝 검사 메서드
    public static void checkParenthesis(String str) {
        Stack stack = new Stack();
        boolean chkFlag = true; // 괄호 짝 여부를 확인하기위한 변수

        // String을 한 글자씩 떼서 검사
        for(String s : str.split("")) {
            if("(".equals(s)) {
                stack.push(s);
            } else {
                // 스택이 빈 경우 더 이상 검사할 필요가 없으므로 break
                if(stack.isEmpty()) {
                    chkFlag = false;
                    break;
                } else { // 그렇지 않으면 스택에서 여는 괄호 제거
                    stack.pop();
                }
            }
        }
        // 괄호 짝 여부가 true 이며 스택도 비었을 때 Pass
        if(chkFlag && stack.isEmpty()) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
    }

    public static void main(String[] args) {
        checkParenthesis("("); // Fail
        checkParenthesis("("); // Fail
        checkParenthesis("()"); // Pass
        checkParenthesis("()()()"); // Pass
        checkParenthesis("(())()"); // Pass
        checkParenthesis("(((()))"); // Fail
        checkParenthesis("(((()))))"); // Fail
    }
}
