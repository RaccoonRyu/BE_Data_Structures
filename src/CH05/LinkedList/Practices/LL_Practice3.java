package CH05.LinkedList.Practices;

// Practice3
// 연결 리스트 부분 뒤집기 (단방향 연결 리스트)
// 주어진 연결 리스트에서 시작 위치부터 끝 위치 사이의 노드들을 뒤집으시오.

// 입출력 예시)
// 입력 연결 리스트 : 1, 2, 3, 4, 5
// 시작 위치 : 2, 끝 위치 : 4
// 처음 위치는 1부터라고 가정
// 결과 연결 리스트 : 1, 4, 3, 2, 5

// idea : 연결리스트를 순회하면서 시작점-종료점 사이 노드들을 바꾸면서 연결정보가 유실될 수 있으므로, 이를 임시로 저장할 노드가 필요
// 이후 바꾼 노드들 간의 연결 정보를 변경해줘서 결과를 만들면 된다.

public class LL_Practice3 {
    public static LinkedList reverseList(LinkedList list, int left, int right) {
        // 연결 정보들을 담기 위한 변수
        Node cur1 = null;
        Node pre1 = null;

        cur1 = list.head;
        // list의 특정 지점(left 전까지)을 head로 부터 탐색
        // left - 1 위치까지 cur1을 이동하면서 pre1과 연결 상태를 유지
        for (int i = 0; i < left - 1; i++) {
            // pre1은 기존의 cur1(head)을 따라가며 저장
            pre1 = cur1;
            // cur1은 cur1의 다음 노드를 가리킨다.
            cur1 = cur1.next;
        }

        Node cur2 = cur1; // 현재 역순으로 연결할 노드, 반복이 진행될 때마다 다음 노드로 이동하여 구간 내 모든 노드를 역순으로 연결
        Node pre2 = pre1; // cur2가 가리켜야 하는 이전 노드를 나타내고, 역순 연결 작업이 수행될 때마다 갱신
        Node after = null; // 다음 노드 정보를 임시로 저장하여 cur2.next가 끊겨도 연결 리스트의 원래 순서를 잃지 않도록하는 변수
        // left부터 right 구간의 노드들을 역으로 연결
        for (int i = left; i <= right; i++) {
            // 연결 정보를 swap
            after = cur2.next; // 다음 노드를 임시로 저장해두기
            cur2.next = pre2; // 현재 노드의 next를 이전 노드로 변경 (역순 연결)
            pre2 = cur2; // pre2는 cur2로 업데이트하여 다음 역순에 대비
            cur2 = after; // cur2는 다음 노드(after)로 이동
        }

        // 변경되지 않은 나머지 연결 정보 정리
        pre1.next = pre2; // left 이전 구간의 끝을 역순으로 변경된 구간의 시작과 연결
        cur1.next = cur2; // 역순으로 변경된 구간의 끝을 right 이후의 구간과 연결

        // 새롭게 연결 정보가 바뀐 연결 리스트 반환
        return list;
    }

    public static void main(String[] args) {
        LinkedList myList1 = new LinkedList();
        myList1.addData(1);
        myList1.addData(2);
        myList1.addData(3);
        myList1.addData(4);
        myList1.addData(5);
        myList1.showData(); // 1 2 3 4 5
        myList1 = reverseList(myList1, 2, 4);
        myList1.showData(); // 1 4 3 2 5

        LinkedList myList2 = new LinkedList();
        myList2.addData(1);
        myList2.addData(2);
        myList2.addData(3);
        myList2.addData(4);
        myList2.addData(5);
        myList2.addData(6);
        myList2.addData(7);
        myList2.showData(); // 1 2 3 4 5 6 7
        myList2 = reverseList(myList2, 3, 5);
        myList2.showData(); // 1 2 5 4 3 6 7
    }
}
