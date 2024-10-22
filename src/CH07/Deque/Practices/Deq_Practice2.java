package CH07.Deque.Practices;

// Practice2
// Palindrome 찾기
// Palindrome 이면 true, 아니면 false 반환
// Palindrome : 앞으로 읽어도 거꾸로 읽어도 같은 문자열

// 입출력 예시)
// 입력 : a
// 결과 : true

// 입력 : madam
// 결과 : true

// 입력 : abab
// 결과 : false

import java.util.ArrayDeque;
import java.util.Deque;

public class Deq_Practice2 {

    public static boolean checkPalindrome(String str) {
        // 문자열 검사를 위한 데크 생성
        Deque deque = new ArrayDeque();
        // 문자열 검사를 위한 boolean 변수 생성
        boolean isFront = true;
        boolean isPalindrome = true;

        // 문자열 검사를 위해 String 데이터를 한글자씩 잘라서 데크로 넣는 부분 구현
        for(String s : str.split("")) {
            deque.addFirst(s);
        }

        // 데크가 빌 때 까지 loop 돌며 앞쪽 글자 한개 - 뒷쪽 글자 한개 비교하기
        while (!deque.isEmpty()) {
            // remove를 사용하면 데크에서 데이터를 빼고 데크에 남은 데이터가 없을 경우 예외를 발생하고 프로그램 종료하기 때문에 poll 사용!
            // poll은 데이터가 없으면 null을 반환하기 때문!
            String s1 = (String)deque.pollFirst();
            String s2 = (String)deque.pollLast();

            // s1과 s2가 null이 아니면서 Palindrome이 아닌 경우 -> 나머지 비교할 필요 없으므로 false 반환하고 탈출
            if(s1 != null && s2 != null && !s1.equals(s2)) {
                isPalindrome = false;
                break;
            }
            // 만약 abcba 같은 홀수 개수의 문자열이 오는 경우에는 s1은 c가 남고 s2가 null인데, 이 경우에는 if문을 내부를 거치지 않아 isPalindrome이 true인 채로 데크가 비게된다.
            // 어차피 남는 문자에 어떤 글자가 오든 문자의 좌우로 같은 문자면 Palindrome이기 때문!
        }

        return isPalindrome;
    }

    public static void main(String[] args) {
        System.out.println(checkPalindrome("a")); // true
        System.out.println(checkPalindrome("aba")); // true
        System.out.println(checkPalindrome("abba")); // true
        System.out.println(checkPalindrome("abab")); // false
        System.out.println(checkPalindrome("abcba")); // true
        System.out.println(checkPalindrome("abccba")); // true
        System.out.println(checkPalindrome("madam")); // true
    }
}
