package CH05.LinkedList;

// Practice2
// 양방향 연결 리스트 (Doubly Linked List) 구현
// 링크가 두개!

class NodeBi {
    int data;
    
    // 양방향 연결이기 때문에 링크가 두개
    NodeBi next;
    NodeBi prev;

    NodeBi(int data, NodeBi next, NodeBi prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    } // 이후해당 노드를 이용해서 양방향 연결 리스트 클래스 생성
}

// 기존의 LinkedList 상속하여 생성
class DoublyLinkedList extends LinkedList {
    // 양방향 연결 리스트는 가장 처음 노드인 head와 가장 끝 노드인 tail을 가진다.
    NodeBi head;
    NodeBi tail;

    DoublyLinkedList(NodeBi node) {
        // 노드가 새로 만들어져서 해당 양방향 연결리스트가 생성되는 경우
        // head와 tail이 같은 노드를 가리킨다.
        this.head = node;
        this.tail = node;
    }

    // node의 타입이 변경되었기 때문에 isEmpty는 새로 작성
    public boolean isEmpty() {
        if(this.head == null) {
            return true;
        }
        return false;
    }

    // 데이터 추가
    public void addData(int data, Integer beforeData) {
        // 양방향 연결 리스트에 데이터가 없는 경우
        if(this.head == null) {
            // 새 노드를 만들면서 data와 next/prev를 null로 넣고 head가 해당 노드를 가리키게 한다.
            this.head = new NodeBi(data, null, null);
            // 가장 첫 데이터이자 마지막 데이터 이므로 tail 또한 head를 가리키게 한다
            this.tail = this.head;
        } else if (beforeData == null) { // 데이터를 가장 끝에 추가하는 경우
            // 가장 끝을 가리키는 tail을 관리하기 때문에 tail을 이용하여 바로 추가 가능
            // 기존의 가장 끝 노드 다음에 새 노드를 만들어서 가리키게 한다.
            this.tail.next = new NodeBi(data, null, tail); // 이 때 새 노드는 다음 노드는 없지만 이전 노드인 tail이 있으므로 prev에는 tail을 넣어준다.
            this.tail = this.tail.next; // 그리고 연결 리스트의 tail은 위에서 새로 생성한 노드가 되도록 설정한다.
        } else { // beforeData가 있는 경우 (맨 앞 or 중간)
            // 찾을 노드는 cur, 그 이전 노드를 따라다니는 직전 노드는 pre 변수에 할당
            NodeBi cur = this.head;
            NodeBi pre = cur;
            while (cur != null) {
                // beforeData와 같은 data를 가진 cur를 찾았을 때 cur 앞에다가 node를 추가
                if(cur.data == beforeData) {
                    // 찾은 노드가 head인 경우 (맨 처음 데이터 추가)
                    if(cur == this.head) {
                        // head가 새롭게 만든 노드를 가리키게 하고, 새 노드는 다음 노드로 기존 head였던 노드를 가리키게 한다.
                        this.head = new NodeBi(data, this.head, null);
                        // 새 노드의 next(=기존 head였던 노드)의 이전 노드는 바뀐 head를 가리키도록 한다.
                        this.head.next.prev = this.head;
                    } else {
                        // 찾은 노드가 head가 아닌 경우 (중간에 데이터 추가)
                        // cur를 따라다니는 직전노드 pre 다음(pre.next)에 새 노드를 생성하여 할당해준다.
                        // 이 때 새 노드는 next로 cur을, prev으로 pre를 가리킨다.(두 노드 사이에 들어간다.)
                        // 따라서 pre는 이제 next로 cur을 가리키는 것이 아니라 pre.next를 가리킨다!
                        pre.next = new NodeBi(data, cur, pre);
                        // cur의 이전 노드는 새로 생성한 노드인 pre.next를 가리킨다.
                        cur.prev = pre.next;
                    }
                    break;
                }
                // cur를 못 찾으면 pre와 cur 이동
                pre = cur;
                cur = cur.next;
            }
        }
    }

    // 데이터 삭제
    public void removeData(int data) {
        if(this.isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        // 노드 처음부터 삭제할 노드까지 순회하기 위한 변수
        NodeBi cur = this.head;
        NodeBi pre = cur;

        while (cur != null) {
            // data에 해당하는 값을 가진 node를 찾은 경우
            if(cur.data == data) {
                // 삭제할 cur이 Head이자 Tail인 경우 (연결리스트 안에 Node가 하나인 경우)
                if(cur == this.head && cur == this.tail) {
                    // 해당 노드가 삭제되면 head와 tail 모두 아무것도 남지 않도록 한다.
                    this.head = null;
                    this.tail = null;
                } else if (cur == this.head) { // 삭제할 cur이 헤드이고 연결리스트 안에 다른 노드들이 있는 경우
                    // 기존 head를 삭제하기 위해 head를 cur의 다음 노드로 가리키고, 새 head로 바뀐 노드의 prev 노드는 null로 변경
                    this.head = cur.next;
                    this.head.prev = null;
                } else if (cur == this.tail) { // 삭제할 cur이 tail인 경우
                    // 기존 tail을 삭제하기 위해 tail을 cur의 바로 앞 노드로 가리키고(this.tail.prev)
                    this.tail = this.tail.prev;
                    // 새 tail로 바뀐 노드(this.tail.prev)의 next 노드는 null로 변경
                    this.tail.next = null;
                } else { // 중간 노드를 삭제하는 경우
                    // pre의 next 노드가 cur의 next 노드를 가리키고, cur 다음 노드의 이전 노드는 pre를 가리키게 변경한다.
                    pre.next = cur.next;
                    cur.next.prev = pre; // cur.next.prev : 원래는 삭제 대상 노드를 가리키지만, 해당 노드의 이전인 pre를 가리키도록 한다.
                }
                break;
            }
            // data에 해당하는 값을 가진 node가 없는 경우 pre와 cur 이동하며 순회하도록 증가
            pre = cur;
            cur = cur.next;
        }
    }

    // 출력 메서드
    public void showData() {
        if(this.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        // 연결리스트의 처음부터 끝까지 순회하며 출력
        NodeBi cur = this.head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    // 역순 출력 메서드
    public void showDataFromTail() {
        if(this.isEmpty()) {
            System.out.print("List is empty!");
            return;
        }

        // 양방향이므로 tail부터 이전을 가리키는 링크를 통해 출력한다.
        // 연결리스트의 끝부터 처음까지 순회하며 출력
        NodeBi cur = this.tail;
        while(cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.prev;
        }
        System.out.println();
    }
}

public class LinkedList_Practice2 {
    public static void main(String[] args) {
        // 양방향 연결 리스트를 만들 때 1 데이터 추가하여 만듦
        DoublyLinkedList myList = new DoublyLinkedList(new NodeBi(1,null, null));
        myList.showData(); // 1

        myList.addData(2, null);
        myList.addData(3, null);
        myList.addData(4, null);
        myList.addData(5, null);
        myList.showData(); // 1 2 3 4 5
        myList.showDataFromTail(); // 5 4 3 2 1

        myList.addData(100, 1); // 1 앞에 100이라는 데이터 삽입
        myList.addData(200, 2);
        myList.addData(300, 3);
        myList.addData(400, 4);
        myList.addData(500, 5);
        myList.showData(); // 100 1 200 2 300 3 400 4 500 5
        myList.showDataFromTail(); // 5 500 4 400 3 300 2 200 1 100

        myList.removeData(300);
        myList.removeData(100);
        myList.removeData(500);
        myList.removeData(200);
        myList.removeData(400);
        myList.showData(); // 1 2 3 4 5
        myList.showDataFromTail(); // 5 4 3 2 1

        myList.removeData(3);
        myList.removeData(1);
        myList.removeData(5);
        myList.removeData(2);
        myList.removeData(4);
        myList.showData(); // List is Empty!
        myList.showDataFromTail(); // List is Empty!
    }
}
