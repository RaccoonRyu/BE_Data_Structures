package CH05.LinkedList;

// Practice3
// 원형 연결 리스트 구현 (Circular Linked List) -> 양방향

class CircularLinkedList {
    // 양방향이므로 Node는 두개
    NodeBi head;
    NodeBi tail;

    CircularLinkedList(NodeBi node) {
        this.head = node;
        this.tail = node;
        // 원형 리스트 이므로 자기 자신으로 다시 순환할 수 있게 필요한 변수 생성
        node.next = this.head;
        node.prev = this.head;
    }

    public boolean isEmpty() {
        if(this.head == null) {
            return true;
        }
        return false;
    }

    // 연결 리스트에 데이터 추가
    // before_data가 null인 경우 가장 뒤에 추가
    // before_data에 값이 있는 경우, 해당 값을 가진 노드 앞에 추가
    public void addData(int data, Integer beforeData) {
        // 헤드에 아무 것도 없는 경우
        if(this.head == null) {
            NodeBi newNodeBi = new NodeBi(data, null, null);
            // 노드가 하나이므로 head, tail, next, prev 모두 newNodeBi를(자기 자신) 가리킴
            this.head = newNodeBi;
            this.tail = newNodeBi;
            newNodeBi.next = newNodeBi;
            newNodeBi.prev = newNodeBi;
        } else if (beforeData == null) { // 데이터를 가장 끝에 추가하는 경우
            // data, next에는 head, prev에는 tail을 넣어 새 노드 생성 -> 원형이기 때문에 next는 가장 끝 노드가 한바퀴 돌아 처음인 head를 가리킨다.
            NodeBi newNodeBi = new NodeBi(data, this.head, this.tail);
            // 기존 tail의 다음 노드는 새로 생성한 노드를 가리키고
            this.tail.next = newNodeBi;
            // 기존 head의 이전 노드는 가장 앞 노드가 뒤로 한바퀴 돌아 맨 뒤인 새로 생성한 노드를 가리킨다.
            this.head.prev = newNodeBi;
            // 새로 생성한 노드를 리스트의 tail로 설정
            this.tail = newNodeBi;
        } else { // beforeData가 있는 경우 (맨 앞 or 중간)
            NodeBi cur = this.head;
            NodeBi pre = cur;
            do {
                // beforeData와 같은 data를 가진 cur를 찾았을 때 cur 앞에다가 node를 추가
                if(cur.data == beforeData) {
                    // head의 앞 부분에 data를 추가
                    if(cur == this.head) {
                        // 노드를 새로 만들 때 해당 노드가 head보다 앞이므로 next는 head, prev는 가장 앞 노드가 뒤로 한바퀴 돌아 tail을 가리킨다.
                        NodeBi newNodeBi = new NodeBi(data, this.head, this.tail);
                        // 기존 tail의 next는 가장 끝 노드가 앞으로 한바퀴 돌아 새로 생성한 노드를 가리키고
                        this.tail.next = newNodeBi;
                        // 기존 head의 prev는 새로 생성한 노드가 가장 앞으로 오기 때문에 새로 생성한 노드를 가리킨다.
                        this.head.prev = newNodeBi;
                        // 새로 생성한 노드를 리스트의 head로 설정한다
                        this.head = newNodeBi;
                    } else { // 찾은 노드가 head가 아닌 경우 (중간에 데이터 추가)
                        // cur를 따라다니는 직전노드 pre 다음(pre.next)에 새 노드를 생성하여 할당해준다.
                        // 이 때 새 노드는 next로 cur을, prev으로 pre를 가리킨다.(두 노드 사이에 들어간다.)
                        NodeBi newNodeBi = new NodeBi(data, cur, pre);
                        // 따라서 pre는 이제 next로 cur을 가리키는 것이 아니라 pre.next를 가리킨다!
                        pre.next = newNodeBi;
                        // cur의 이전 노드는 새로 생성한 노드인 pre.next를 가리킨다.
                        cur.prev = newNodeBi;
                    }
                    break;
                }
                pre = cur;
                cur = cur.next;
            } while (cur != this.head);
        }
    }

    // 연결 리스트에서 특정 값을 가진 노드 삭제
    public void removeData(int data) {
        if(this.isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        // 삭제를 위한 노드 변수 두개 생성
        NodeBi cur = this.head;
        NodeBi pre = cur;
        while (cur != null) {
            // 삭제하려는 데이터와 노드(cur)가 가진 데이터가 같은 경우 -> 삭제
            if(cur.data == data) {
                // 노드가 하나 뿐일 때
                if(cur == this.head && cur == this.tail) {
                    this.head = null;
                    this.tail = null;
                } else if(cur == this.head) { // 노드가 헤드일 때 -> 원형 연결 리스트 유지 필요로 인한 주의사항
                    // 현재 삭제하려는 노드의 다음 노드(cur.next = 새 head)의 prev를 기존 head의 prev로 변경
                    // 기존 head를 지우기 때문에 새 head의 prev가 tail을 가리키게 해줘야한다.
                    cur.next.prev = this.head.prev;
                    // head 자리는 새 head(cur.next)를 가리키도록 변경한다.
                    this.head = cur.next;
                    // tail의 다음 노드는 새 head를 가리키도록 변경한다.
                    this.tail.next = this.head;
                } else if(cur == this.tail) { // 노드가 tail일 때 -> 원형 연결 리스트 유지 필요로 인한 주의사항
                    // 삭제대상(cur = 현재 tail)이 가장 끝 노드아므로 바로 직전 노드(pre)의 next가 현재 tail의 next를 가리키도록 변경
                    pre.next = this.tail.next;
                    // 이후 tail 자리는 삭제대상 대신 직전 노드(pre)를 가리키도록 변경한다.
                    this.tail = pre;
                    // head의 이전 노드는 새 tail을 가리키도록 변경한다.
                    this.head.prev = this.tail;
                } else { // 노드가 중간에 있을 때
                    pre.next = cur.next;
                    cur.next.prev = pre;
                }
                break;
            }

            pre = cur;
            cur = cur.next;
        }
    }

    public void showData() {
        if(this.isEmpty()) {
            System.out.println("List is Empty");
        }

        NodeBi cur = this.head;
        // 주의 : 원형 연결리스트이기 때문에 끝이 처음과 이어져있으므로 헤드 전까지 출력한다. (null로 끝내면 무한 루프에 빠짐)
        while (cur.next != this.head) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        // 위의 loop를 수행하면 가장 끝 노드의 데이터가 출력되지 않아 아래와 같이 출력하고 마침
        System.out.println(cur.data);
    }
}

public class LinkedList_Practice3 {
    public static void main(String[] args) {
        CircularLinkedList myList = new CircularLinkedList(new NodeBi(1, null, null));

        myList.addData(2, null);
        myList.addData(3, null);
        myList.addData(4, null);
        myList.addData(5, null);
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
        myList.showData(); // List is Empty!

    }
}
