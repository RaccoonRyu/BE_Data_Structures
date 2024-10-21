package CH01.Stack.Practices;

// Practice1
// 문자열 뒤집기

// 입출력 예시
// 입력 : "Hello"
// 출력 : "olleH"

// 입력 : 1 3 5 7 9
// 출력 : 9 7 5 3 1

import java.util.Stack;

public class St_Practice1 {

    public static String reverseString(String str) {
        // 문자열 처리에 사용할 Stack과 반전할 문자열을 담을 변수 생성
        Stack stack = new Stack();
        String reverseStr = "";

        // 문자열 내의 한 글자씩 뜯어서 스택에 push
        // split("") : 문자열 내 글자를 한글자씩 뜯어서 반복문 수행 가능
        for(String s: str.split("")) {
            stack.push(s);
        }

        // 반복문 수행을 통해 스택에서 pop한 결과를 반전 문자열 변수에 담기
        // 반복 조건은 스택의 empty 여부 체크
        while(!stack.isEmpty()) {
            reverseStr += stack.pop();
        }

        return reverseStr;
    }

    public static void main(String[] args) {
        String resultS = reverseString("Hello");
        System.out.println(resultS);
        String resultI = reverseString("1 3 5 7 9");
        System.out.println(resultI);
    }
}
