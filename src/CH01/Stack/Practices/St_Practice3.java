package CH01.Stack.Practices;

// Practice3
// 후위 표기법 연산
// 참고 - 전위/중위/후위 표기법
// 전위 표기법 : 연산자를 두 숫자의 앞으로 보내는 표기법
// 중위 표기법 : 숫자와 숫자 사이에 연산자가 있는 표기법
// 후위 표기법 : 연산자를 두 숫자의 뒤로 보내는 표기법
// 후위 표기법 tip -> 연산자 우선순위에 따라 식의 모든 연산을 괄호로 싼 다음, 연산자를 가장 가까운 괄호의 뒤쪽으로 옮기면 된다. (전위는 앞쪽으로 옮기면 됨!)
// 다시 중위 표기법으로 변환할 때는 가장 가까운 두 숫자 사이로 연산자를 옮기면 된다.

// Stack을 통한 후위 표기법 연산 Key
// 입력으로 숫자와 연산자가 포함된 문자열을 받으면 연산자를 만나기 전까지의 숫자를 스택에 넣어준다.
// 이후 연산자를 만나면 스택으로부터 두개 숫자를 pop하여 연산을 수행한다.
// 뺄셈 연산의 경우 Stack에서 먼저 나온 수가 뒤에 나온 수보다 적을 수 있으므로, 먼저 나온 수에 -(마이너스)를 붙여서 뒤에 나온수를 더해준다.

// 입출력 예시
// 입력 : 2 2 +
// 출력 : 4

// 입력 : 2 2 -
// 출력 : 0

import java.util.Stack;

public class St_Practice3 {

    static double calculate (String str) {
        Stack<Double> stack = new Stack(); // return을 double로 해야하기 때문에 Stack의 제네릭 또한 Double

        // split(" ") : 문자열 내 공백을 사용하지 않기 위해 스페이스 1개 단위로 문자열의 한 글자씩 떼냄
        for(String s : str.split(" ")) {
            if("+".equals(s)) {
                // 두개 숫자를 pop하고 더해서 다음 연산에서도 사용할 수 있도록 연산 결과를 다시 push
                stack.push(stack.pop() + stack.pop());
            } else if("-".equals(s)) {
                // 먼저 꺼낸 수가 뒤에 꺼낸 수 보다 적을 수 있으므로 먼저 나온 수에 -를 붙여 뒤에 나온수를 더함
                stack.push(- stack.pop() + stack.pop());
            } else if ("*".equals(s)) {
                stack.push(stack.pop() * stack.pop());
            } else if ("/".equals(s)) {
                // 나누기 또한 먼저 꺼낸 수로 뒤에 꺼낸 수를 나눠야 하므로 1을 앞에 나온 수로 나눠 분모를 만들고 뒤에 꺼낸수를 곱해준다.
                stack.push(1 / stack.pop() * stack.pop());
            } else {
                // 연산자가 아닌 숫자가 들어온 경우 숫자로 변경하여 push
                stack.push(Double.parseDouble(s));
            }
        }

        // 반복문 수행 후에는 연산 결과만 스택에 존재하므로 pop하여 반환
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(calculate("2 2 +")); // 4
        System.out.println(calculate("2 2 -")); // 0
        System.out.println(calculate("2 2 *")); // 4
        System.out.println(calculate("2 2 /")); // 1

        System.out.println(calculate("1 1 + 2 * 3 * 2 / 5 -")); // 1
        System.out.println(calculate("5 2 * 3 - 8 * 4 /")); // 14
    }
}
