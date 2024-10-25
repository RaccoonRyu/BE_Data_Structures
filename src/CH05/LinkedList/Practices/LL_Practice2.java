package CH05.LinkedList.Practices;

// Practice2
// Palindrome 연결 리스트
// 추가 자료구조 없이 연결 리스트로만 풀어보기 (단방향 연결 리스트)
// Palindrome : 앞으로 읽어도 뒤로 읽어도 같은 문자열

// 입출력 예시)
// 입력 연결 리스트 : 1, 3 , 5, 3, 1
// 결과 연결 리스트 : true

// 입력 연결 리스트 : 3, 5, 5, 3
// 결과 연결 리스트 : true

// 입력 연결 리스트 : 1, 3, 5, 1
// 결과 연결 리스트 : false

// idea : 가장 앞과 가장 끝을 비교해서 같은지를 확인 -> 가장 왼쪽과 가장 오른쪽을 움직여가며 비교
// 이 때, left는 계속 증가하고, right는 양방향이 아니기 때문에 이전 right가 아닐 때 까지 left를 따라오며? 가리키도록 한다.

public class LL_Practice2 {
    // Palindrome 검사 함수
    public static boolean checkPalindrome(LinkedList list) {
        Node cur = list.head;
        Node left = list.head;
        Node right = null;

        // cnt : 연결 리스트 내 원소가 몇개인지 세기위한 변수
        int cnt = 0;
        while (cur != null) {
            cnt++;
            // right가 가장 끝을 가리킬 수 있도록 cur을 따라가며 이동시킴
            right = cur;
            cur = cur.next;
        } // loop가 끝나면 cur는 null, right는 가장 끝 노드를 가리킴

        // right 노드를 하나 이전으로 계속 검색하기 위한 변수
        Node prevRight = right;
        // 연결리스트 원소 개수/2 만큼 loop 수행 -> 원소개수 절반만큼 비교했을 때 같으면 Palindrome이므로! ex) 1 3 절[5]반 3 1
        for (int i = 0; i < cnt/2; i++) {
            // Palindrome이 아닌 상태
            if(left.data != right.data) {
                return false;
            }

            // 데이터가 같았다면
            left = left.next;
            // right는 left부터 이전의 right(prevRight)를 만날 때 까지 이동시킴
            right = left;
            // right가 이전의 right전(prevRight가 아닐 때)까지 loop 수행하여 이동
            while (right.next != prevRight) {
                right = right.next;
            }
        }
        // 두 데이터가 계속 다 같았다면 위 반복문 내 조건문에서 false가 return되지 않으므로 true 반환
        return true;
    }

    public static void main(String[] args) {
        LinkedList myList1 = new LinkedList();
        myList1.addData(1);
        myList1.addData(3);
        myList1.addData(5);
        myList1.addData(3);
        myList1.addData(1);
        System.out.println(checkPalindrome(myList1)); // t

        LinkedList myList2 = new LinkedList();
        myList2.addData(3);
        myList2.addData(5);
        myList2.addData(5);
        myList2.addData(3);
        System.out.println(checkPalindrome(myList2)); // t

        LinkedList myList3 = new LinkedList();
        myList3.addData(1);
        myList3.addData(3);
        myList3.addData(5);
        myList3.addData(1);
        System.out.println(checkPalindrome(myList3)); // f

    }
}
