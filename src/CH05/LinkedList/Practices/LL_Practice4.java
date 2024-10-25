package CH05.LinkedList.Practices;

// Practice4
// 연결리스트 배열 사용 연습

// 주어진 문자열 배열을 연결 리스트 배열로 관리하는 코드를 작성
// 관리 규칙 : 각 문자열의 첫 글자가 같은 것 끼리 연결 리스트로 관리

// idea : 각 단어의 첫 글자들의 정보를 가지고 연결 리스트 배열을 만들고, 해당 연결 리스트 안에 같은 단어들을 관리 (ex. a => apple, apricot, ...)
// 연결리스트 배열 생성 (길이 : 문자열 배열 내 단어들의 첫 번째 알파벳들의 개수) > 그 다음 단어의 첫 알파벳과 같으면 연결

import java.util.HashSet;
import java.util.LinkedList;

class Node1 {
    // 노드는 값과 링크를 관리하는 부분으로 이뤄짐
    // 값 (문자열)
    String data;
    // 링크 (클래스 자기 자신을 가리킬 Node 자료형) -> 여러 노드들이 있을 때 그 다음 노드를 가리키기 위한 용도
    Node1 next;

    Node1() {}
    Node1(String data, Node1 next) { // data와 link를 넣어서 노드 생성
        this.data = data;
        this.next = next;
    }
}

class LinkedList1 {
    // head 역할을 하는 Node
    Node1 head;
    // 연결 리스트 배열을 만들때 단어의 첫 글자 관리를 위한 변수
    char alphabet;

    LinkedList1() {}
    LinkedList1(Node1 node, char alphabet) {
        this.head = node;
        this.alphabet = alphabet;
    }

    // 연결 리스트 비어있는지 확인
    public boolean isEmpty() {
        // 헤드가 없다는 것은 연결 리스트가 비어있다는 뜻
        if (this.head == null) {
            return true;
        }
        return false;
    }

    // 연결 리스트의 맨 뒤에 데이터 추가
    public void addData(String data) {
        // 연결리스트가 비어있는 경우 가장 맨 처음인 head에 Node를 생성 및 할당
        if(this.head == null) {
            // 이후 데이터가 없으므로 next Node는 null로 넣는다.
            this.head = new Node1(data, null);
        } else {
            // 연결리스트에 노드가 있는 경우 헤드로부터 끝 노드까지 순회
            // cur : 현재 노드를 가리키는 변수
            Node1 cur = this.head;
            // 현재 노드의 다음 노드가 있을 때 까지 순회한다.
            while (cur.next != null) {
                cur = cur.next;
            }
            // 이후 가장 끝에 노드 생성하여 추가
            cur.next = new Node1(data, null);
        }
    }

    // 연결 리스트의 맨 뒤의 데이터 삭제
    public void removeData(String data) {
        // 삭제할 때 리스트가 비어있으면 삭제할 수 없으므로 메시지 출력
        if(this.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        // 삭제 시 헤드부터 삭제할 노드까지 순회하기 위한 변수 생성
        Node1 cur = this.head; // head부터 삭제할 노드까지 이동할 변수
        Node1 pre = cur; // head부터 삭제할 노드 전까지 이동할 변수 => 맨 마지막 노드가 삭제되면 바로 직전 노드의 링크를 없애주기 위함

        while (cur != null) {
            if(cur.data.equals(data)) { // 문자열 비교는 equals
                if(cur == this.head) {
                    this.head = cur.next;
                } else {
                    pre.next = cur.next;
                }
                break;
            }
            pre = cur;
            cur = cur.next;
        }
    }

    // 연결 리스트에서 데이터 찾기
    public boolean findData(String data) {
        if(this.isEmpty()) {
            System.out.println("List is Empty!");
        }

        // Head부터 리스트를 순회하며 찾고자하는 데이터가 있는지를 검사하는 loop
        Node1 cur = this.head;
        // Head ~ 찾으려는 데이터를 가진 노드 순회
        while (cur != null) {
            // cur의 data와 찾으려는 data와 비교하여 있으면 데이터 있음 메시지 출력
            if (cur.data.equals(data)) {
                //System.out.println("Data exist");
                return true;
            }
            // 찾는 데이터가 없으면 cur 이동
            cur = cur.next;
        }
        // loop 수행 이후에도 찾는 데이터가 없으면 데이터 없음 메시지 출력
        //System.out.println("Data is not found!");
        return false;
    }

    // 연결 리스트의 모든 데이터 출력
    public void showData() {
        if(this.isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        Node1 cur = this.head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}

public class LL_Practice4 {

    // 연결리스틉 배열 생성 및 데이터 연결
    public static void dataCollect(String[] data) {
        // 문자열 배열에서 첫 글자들을 솎아낼 HashSet 생성
        HashSet<Character> set = new HashSet<>();
        // loop 수행하며 각각의 문자열들을 가져와 char 배열로 바꾸고, 첫번째 값(첫번째 알파벳)을 HashSet에 set해줌
        // 이 때 중복된 첫번째 알파벳은 제거됨
        for (String s : data) {
            set.add(s.toCharArray()[0]);
        } // 여기까지 배열에서 첫 번째 알파벳들이 중복되지 않게 set에 모은 상태

        // 해당 set의 size에 맞는 연결리스트 배열을 생성 가능
        System.out.println(set); // set 확인용
        // set에서 만든 data들을 array형태로 만들어 char 값을 담아두기 위한 배열
        Character[] arr = set.toArray(new Character[0]);
        // 연결 리스트 배열 생성 -> 연결 리스트 배열을 위한 공간만 만들었을 뿐, 아직 연결리스트가 배열 내 존재하지 않으므로 접근할 수 없다. 중요!!!!!!
        LinkedList1[] linkedList1s = new LinkedList1[set.size()];
        // 연결리스트 배열의 길이만큼 loop 수행하며 각 위치에 새 LinkedList1 생성해서 넣어줌. 중요 2!!!!!!!!!
        for (int i = 0; i < linkedList1s.length; i++) {
            // 이 때 각 위치에는 알파벳 첫 글자가 담길 수 있도록 해당 정보(arr[i])를 값으로 넣어준다.
            // 즉, 각 알파벳을 가지고 연결 리스트를 구성하는 것이다.
            linkedList1s[i] = new LinkedList1(null, arr[i]);
        }

        // 문자열 배열의 각 문자열들을 순회하며 연결리스트에 연결
        for(String item : data) {
            // list에 있는 알파벳과 item의 첫 글자를 비교하며 연결 리스트에 문자열들을 연결
            for(LinkedList1 list : linkedList1s) {
                if(list.alphabet == item.toCharArray()[0]) {
                    list.addData(item);
                }
            }
        }

        // 출력
        for (LinkedList1 list : linkedList1s) {
            System.out.print(list.alphabet + " : ");
            list.showData();
        }

    }

    public static void main(String[] args) {
        String[] input = {"apple", "watermelon", "banana", "apricot", "kiwi", "blueberry", "orange", "cherry"};
        dataCollect(input);

        String[] input2 = {"ant", "kangaroo", "dog", "cat", "alligator", "duck", "crab", "anaconda", "chicken", "kitten"};
        dataCollect(input2);
    }
}
