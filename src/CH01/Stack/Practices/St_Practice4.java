package CH01.Stack.Practices;

// Practice4
// 두 문자열 비교
// 단, #은 backspace로 바로 이전 문자를 삭제하는 기능이라고 가정

// 입출력 예시
// 입력 : s1 = "tree", s2 = "th#ree"
// 출력 : true

// 입력 : s1 = "wo#w", s2 = "ww#o"
// 출력 : false

import java.util.Stack;

public class St_Practice4 {
    // 두 문자열을 비교하는 함수
    public static boolean stringCompare(String s1, String s2) {
        // doBackSpace 함수에 따라 #을 backspace처럼 기능하여 가공한 문자열 생성
        String s1After = doBackSpace(s1);
        String s2After = doBackSpace(s2);
        // 문자열을 비교한 결과 반환
        return s1After.equals(s2After);
    }

    // 매개변수로 들어오는 스트링을 조건에 맞게 스택으로 가공할 함수
    public static String doBackSpace(String str) {
        Stack stack = new Stack<>();

        // split() 대신 String에서 char 타입 글자로 하나씩 뜯어오기 위해 String을 char Array로 변경한다.
        for(char c: str.toCharArray()) {
            // 떼온 글자가 #과 같으며 스택이 비어있지 않으면 pop
            if(c == '#' && !stack.empty()) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        // 반복문을 모두 수행하면 스택에 있는 결과를 String 타입으로 변환하여 반환
        return String.valueOf(stack);
    }

    public static void main(String[] args) {
        String s1 = "tree";
        String s2 = "th#ree";
        System.out.println(stringCompare(s1, s2)); // true

        s1 = "ab#a";
        s2 = "aab#";
        System.out.println(stringCompare(s1, s2)); // true

        s1 = "wo#w";
        s2 = "ww#o";
        System.out.println(stringCompare(s1, s2)); // false
    }
}
