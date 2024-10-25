package CH05.LinkedList;

// 선형 자료구조 - 연결 리스트
// 단순 연결 리스트 (simple ver) 기본 구조 구현

// 노드 - 연결리스트의 기본 단위
class Node {
    // 노드는 값과 링크를 관리하는 부분으로 이뤄짐
    // 값
    int data;
    // 링크 (클래스 자기 자신을 가리킬 Node 자료형) -> 여러 노드들이 있을 때 그 다음 노드를 가리키기 위한 용도
    Node next;

    Node() {}
    Node(int data, Node next) { // data와 link를 넣어서 노드 생성
        this.data = data;
        this.next = next;
    }
}

class LinkedList {
    // head 역할을 하는 Node
    Node head;

    LinkedList() {}
    LinkedList(Node node) {
        this.head = node;
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
    public void addData(int data) {
        // 연결리스트가 비어있는 경우 가장 맨 처음인 head에 Node를 생성 및 할당
        if(this.head == null) {
            // 이후 데이터가 없으므로 next Node는 null로 넣는다.
            this.head = new Node(data, null);
        } else {
            // 연결리스트에 노드가 있는 경우 헤드로부터 끝 노드까지 순회
            // cur : 현재 노드를 가리키는 변수
            Node cur = this.head;
            // 현재 노드의 다음 노드가 있을 때 까지 순회한다.
            while (cur.next != null) {
                cur = cur.next;
            }
            // 이후 가장 끝에 노드 생성하여 추가
            cur.next = new Node(data, null);
        }
    }

    // 연결 리스트의 맨 뒤의 데이터 삭제
    public void removeData() {
        // 삭제할 때 리스트가 비어있으면 삭제할 수 없으므로 메시지 출력
        if(this.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        // 삭제 시 헤드부터 삭제할 노드까지 순회하기 위한 변수 생성
        Node cur = this.head; // head부터 삭제할 노드까지 이동할 변수
        Node prev = cur; // head부터 삭제할 노드 전까지 이동할 변수 => 맨 마지막 노드가 삭제되면 바로 직전 노드의 링크를 없애주기 위함

        // 헤드부터 삭제할 노드까지 순회
        while (cur.next != null) {
            prev = cur; // prev는 cur 노드를 할당 (cur 하나 이전의 노드로 이동)
            cur = cur.next; // cur은 다음 노드로 이동
        }

        // 삭제할 노드가 헤드라면 헤드 참조 제거 (head 뒤에 아무 노드도 없음)
        if(cur == this.head) {
            this.head = null;
        } else {
            // 삭제할 노드가 prev 다음 노드라면 prev의 next 참조 제거
            prev.next = null;
        }
    }

    // 연결 리스트에서 데이터 찾기
    public void findData(int data) {
        if(this.isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        // Head부터 리스트를 순회하며 찾고자하는 데이터가 있는지를 검사하는 loop
        Node cur = this.head;
        // Head ~ 찾으려는 데이터를 가진 노드 순회
        while (cur != null) {
            // cur의 data와 찾으려는 data와 비교하여 있으면 데이터 있음 메시지 출력
            if (cur.data == data) {
                System.out.println("Data exist");
                return;
            }
            // 찾는 데이터가 없으면 cur 이동
            cur = cur.next;
        }
        // loop 수행 이후에도 찾는 데이터가 없으면 데이터 없음 메시지 출력
        System.out.println("Data is not found!");
    }

    // 연결 리스트의 모든 데이터 출력
    public void showData() {
        if(this.isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}

public class LinkedList_Lecture {
    public static void main(String[] args) {
        // 연결 리스트 예제 코드
        // LinkedList 객체 생성할 때 head로 사용할 Node 생성하여 넣어줌
        LinkedList myList = new LinkedList(new Node(1, null));
        myList.showData(); // 1

        myList.addData(2);
        myList.addData(3);
        myList.addData(4);
        myList.addData(5);
        myList.showData(); // 1 2 3 4 5

        myList.findData(3); // data exist
        myList.findData(100); // data not found

        myList.removeData();
        myList.removeData();
        myList.removeData();
        myList.showData(); // 1 2

        myList.removeData();
        myList.removeData();
        myList.removeData(); // List is empty
    }
}
