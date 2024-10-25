package CH05.LinkedList;

// Practice1
// 단순 연결 리스트 구현 완성
// 중간에서 데이터 추가/삭제하는 로직 생성

// 중간에서 데이터 추가/삭제하는 로직을 생성하기 위해 Lecture에서 생성한 LinkedList 상속
class LinkedList2 extends LinkedList {

    LinkedList2(Node node) {
        this.head = node; // super해도 무관 (연결리스트2에는 head가 없고, 연결리스트에서 상속받은 head를 사용하기 때문)
    }

    // 연결 리스트 중간에 데이터 추가
    // before_data가 null인 경우 가장 뒤에 추가
    // before_data에 값이 있는 경우, 해당 값을 가진 노드 앞에 추가
    // 추가할 data 외에 beforeData를 매개변수로 받아 beforeData 앞에 추가할지 가장 뒤에 추가할지를 결정
    public void addData(int data, Integer beforeData) {
        // Head에 아무것도 없는 경우 새 노드 만들어서 할당
        if(this.head == null) {
            this.head = new Node(data, null);
        } else if(beforeData == null) { // beforeData가 없는 경우 가장 뒤에 데이터 추가
            Node cur = this.head;
            // head에서 끝 노드까지 순회
            while (cur.next != null) {
                cur = cur.next;
            }
            // 이후 가장 끝 노드 뒤에다가 방금 들어온 데이터를 새 노드로 생성하여 할당
            cur.next = new Node(data, null);
        } else {
            // beforeData 사용하여 추가
            // 가장 끝 노드를 가리킬 cur과 cur의 직전 노드를 가리킬 pre 변수 생성
            Node cur = this.head;
            Node pre = cur;
            while (cur != null) {
                // 연결 리스트 내에서 beforeData에 해당하는 값을 가진 노드를 찾은 상태
                if(cur.data == beforeData) {
                    // cur이 가장 앞 head인 경우 head에 방금 들어온 데이터를 Node로 만들어 할당한다.
                    // 이 때 before_data에 값이 있는 경우, 해당 값을 가진 노드 앞에 추가해야 하므로 새 노드의 다음 노드는 이전 헤드 노드이다.
                    if(cur == this.head) {
                        this.head = new Node(data, this.head);
                    } else {
                        // 연결리스트 중간에 들어가는 경우에는 새 노드 만든 다음 cur의 직전을 따라다니는 pre의 next에 할당한다.
                        // 이 때, pre가 cur을 따라다니므로 새 노드의 next는 cur이다.
                        pre.next = new Node(data, cur);
                    }
                    break;
                }
                // pre와 cur 이동
                pre = cur;
                cur = cur.next;
            }
        }
    }

    public void removeData(int data) {
        if(this.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        // data에 해당하는 노드 삭제 -> 가장 앞일수도, 중간일수도, 뒤일수도 있음
        // 삭제할 노드인 cur과 그 직전 노드인 pre 변수 생성
        Node cur = this.head;
        Node pre = cur;
        while (cur != null) {
            // 지우려는 data가 cur의 data와 동일
            if (cur.data == data) {
                // cur이 head인 경우 head가 cur의 다음 노드를 가리키도록 변경
                if(cur == this.head) {
                    this.head = cur.next;
                } else { // 그 외의 경우에는 이전 노드의 다음 노드가 cur의 다음 노드를 가리키도록 변경
                    pre.next = cur.next;
                }
                break;
            }
            pre = cur;
            cur = cur.next;
        }
    }
}

public class LinkedList_Practice1 {
    public static void main(String[] args) {
        LinkedList2 myList = new LinkedList2(new Node(1,null));
        myList.showData(); // 1

        myList.addData(2);
        myList.addData(3);
        myList.addData(4);
        myList.addData(5);
        myList.showData(); // 1 2 3 4 5

        myList.addData(100, 1); // 1 앞에 100이라는 데이터 삽입
        myList.addData(200, 2);
        myList.addData(300, 3);
        myList.addData(400, 4);
        myList.addData(500, 5);
        myList.showData(); // 100 1 200 2 300 3 400 4 500 5

        myList.removeData(300);
        myList.removeData(100);
        myList.removeData(500);
        myList.removeData(200);
        myList.removeData(400);
        myList.showData(); // 1 2 3 4 5

        myList.removeData(3);
        myList.removeData(1);
        myList.removeData(5);
        myList.removeData(2);
        myList.removeData(4);
        myList.showData(); // ** List is Empty!
    }

}
