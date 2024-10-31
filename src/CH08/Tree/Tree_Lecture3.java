package CH08.Tree;

// 트리 구조 복습 및 구현
// Java를 통해 트리 구조 용어에 대한 학습

import java.util.LinkedList;
import java.util.Queue;

// parent node를 추가한 변형 노드 사용
class Node2 {
    char data;
    Node2 left;
    Node2 right;
    // 부모 노드 탐색을 위한 parent 노드 변수 추가
    Node2 parent;

    public Node2(char data, Node2 left, Node2 right, Node2 parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}

class BinaryTree3 {
    Node2 head;

    // 트리 생성하며 parent쪽도 연결하는 부분 추가
    BinaryTree3(char[] arr) {
        Node2[] node2s = new Node2[arr.length];
        // 각각의 노드들을 loop 수행하며 초기화
        for (int i = 0; i < arr.length; i++) {
            node2s[i] = new Node2(arr[i], null, null, null);
        }
        
        // 다시 loop 수행하며 범위 비교를 통해 자식 노드에 대한 인덱스 탐색
        for (int i = 0; i < arr.length; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // 인덱스 범위 확인 -> 자식 노드가 있으면 현재 노드의 좌/우측에 해당 노드를 연결
            // 부모 노드 또한 아래에서 연결해준다.
            if(left < arr.length) {
                node2s[i].left = node2s[left];
                node2s[left].parent = node2s[i];
            }

            if(right < arr.length) {
                node2s[i].right = node2s[right];
                node2s[right].parent = node2s[i];
            }
        }
        // head는 루트 노드
        this.head = node2s[0];
    }

    // 레벨 순회하며 data에 해당하는 노드를 찾기위한 함수
    public Node2 searchNode(char data) {
        Queue<Node2> queue = new LinkedList<>();
        // 탐색 시점은 head 부터
        queue.add(this.head);

        // queue가 빌 때 까지 순회하며 해당 노드 찾기
        while (!queue.isEmpty()) {
            Node2 cur = queue.poll();

            // 큐로부터 꺼낸 cur(현재 위치) 노드가 찾으려는 노드인 경우 cur의 노드를 반환
            if(cur.data == data) {
                return cur;
            }

            if(cur.left != null) {
                queue.offer(cur.left);
            }

            if(cur.right != null) {
                queue.offer(cur.right);
            }
        }

        // 모든 데이터를 순회했음에도 데이터가 없으면 null 반환
        return null;
    }

    // 특정 data를 가진 노드의 size를 계산하는 함수
    public Integer checkSize(char data) {
        // data를 가진 노드를 찾음
        Node2 node = this.searchNode(data);

        // queue 이용 -> 해당 위치로부터 레벨 순회를 통해 자식 노드들을 찾을 때 마다 개수(size) 증가
        Queue<Node2> queue = new LinkedList<>();
        queue.add(node);
        int size = 0;
        // loop 수행하며 레벨 순회할 수 있는 노드가 있을 때 마다 개수 증가
        while (!queue.isEmpty()) {
            Node2 cur = queue.poll();

            // 좌/우측 자식 노드에 데이터 추가될 때 마다 자식 노드가 존재한다는 뜻 -> size 증가
            if(cur.left != null) {
                queue.offer(cur.left);
                size++;
            }

            if(cur.right != null) {
                queue.offer(cur.right);
                size++;
            }
        }
        // size를 0부터 시작했으므로 자기 자신을 포함하여 return
        return size + 1;
    }
}

public class Tree_Lecture3 {
    public static void main(String[] args) {
        // 트리 예제 코드
        // 배열이 들어오면 배열의 요소 그 순서대로 이진트리가 구성되었다고 가정
        char[] arr = new char[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ('A' + i); // 알파벳 A부터 총 10개의 데이터가 순서대로 배열에 들어감
        }

        BinaryTree3 bt3 = new BinaryTree3(arr);

        // Root node
        System.out.println("Root : " + bt3.head.data);

        // B's Child node
        Node2 B = bt3.searchNode('B'); // char B를 가진 노드를 찾기위한 searchNode
        // B의 좌/우 자식노드가 없다면 출력
        if(B.left != null) {
            System.out.println("B -> left chile : " + B.left.data);
        }
        if(B.right != null) {
            System.out.println("B -> right chile : " + B.right.data);
        }

        // F's parent node
        Node2 F = bt3.searchNode('F');
        System.out.println("F -> parent node : " + F.parent.data);

        // Leaf node
        // 말단이면서 자식이 없는 노드
        System.out.print("Leaf node : ");
        // 배열에 있는 데이터를 이용하기 위해 for loop 수행하며 각각의 노드들을 우선 탐색
        for (int i = 0; i < arr.length; i++) {
            Node2 cur = bt3.searchNode((char)('A' + i));

            // 찾은 각각의 노드들에 대한 자식 노드를 탐색
            // 좌/우 자식 노드가 없으면 leaf node
            if(cur.left == null && cur.right == null) {
                System.out.print(cur.data + " ");
            }
        }
        System.out.println();

        // E's Depth
        // 루트 노드로부터 E 노드까지의 간선의 길이를 구하면 된다.
        // E까지 몇번을 거쳐서 왔는지 확인하려면 E로부터 부모 노드쪽으로 거슬러 올라가면 된다.
        Node2 E = bt3.searchNode('E');
        Node2 cur = E;
        // 간선의 길이를 구하기 위한 변수
        int cnt = 0;

        // loop 수행하며 parent가 null이 아닐 때 까지 간선의 길이를 증가
        while (cur.parent != null) {
            // loop 수행되는 동안 부모 노드로 올라가며 cnt 증가
            cur = cur.parent;
            cnt++;
        }
        System.out.println("E depth : " + cnt); //

        // Level 2 Node
        // 특정 레벨의 노드들을 출력
        System.out.print("Level 2 node : ");
        // loop 수행하며
        for (int i = 0; i < arr.length; i++) {
            // searchNode로 각각의 노드 탐색
            Node2 target = bt3.searchNode((char) ('A' + i));
            cur = target;
            cnt = 0;

            // 현재 찾은 cur로부터 cur의 부모가 null이 될 때 까지(루트까지) 각 노드의 depth 구함
            while (cur.parent != null) {
                cur = cur.parent;
                cnt++;
            }

            // cnt(depth)가 2면 레벨 2에 해당하는 노드
            if(cnt == 2) {
                System.out.print(target.data + " ");
            }
        }
        System.out.println();

        // Height
        // 트리의 높이 -> 제일 깊은 노드를 찾으면 됨! 각각의 노드들을 순회하며 depth를 구하면 된다.
        // 최댓값은 최초에 비교를 위한 최솟값을 할당
        int maxCnt = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            Node2 target = bt3.searchNode((char)('A' + i));
            cur = target;
            cnt = 0;
            while (cur.parent != null) {
                cur = cur.parent;
                cnt++;
            }

            // 최댓값과 비교하며 최댓값보다도 방금 찾은 depth가 크다면 maxCnt에 cnt를 대입
            if(maxCnt < cnt) {
                maxCnt = cnt;
            }
        }
        System.out.println("Height : " + maxCnt);

        // B's Size
        // B의 노드에 포함된 노드의 총 개수 = B's Size
        int size = bt3.checkSize('B');
        System.out.println("B size : " + size);
    }
}
