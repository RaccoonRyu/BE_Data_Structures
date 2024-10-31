package CH08.Tree;

// 연결 리스트를 이용한 이진 트리 구성 및 순회

import java.util.LinkedList;
import java.util.Queue;

// 연결리스트에서 사용하기 위한 Node 생성
class Node {
    // 배열의 데이터가 char이므로 여기서도 데이터로 char 사용
    char data;
    // 왼쪽, 오른쪽 자식 노드를 가리키기 위한 링크 변수
    Node left;
    Node right;

    public Node(char data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class BinaryTree2 {
    Node head;

    BinaryTree2() {}

    // char 배열을 받아 연결하는 부분 작성
    // char 배열을 Node 형태의 배열로 만든다.
    BinaryTree2(char[] arr) {
        Node[] nodes = new Node[arr.length];
        // for loop 수행하며 각각의 노드 위치에 char 배열 값 이용하여 Node 배열에 Node 생성 및 추가
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i], null, null);
        }

        // 다시 loop 수행하며 현재 노드에 대해 자식 노드들을 연결
        for (int i = 0; i < arr.length; i++) {
            // 자식 노드 연결을 위한 좌/우측 인덱스
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // 인덱스 범위 확인 -> 자식 노드가 있으면 현재 노드의 좌/우측에 해당 노드를 연결
            if(left < arr.length) {
                nodes[i].left = nodes[left];
            }

            if(right < arr.length) {
                nodes[i].right = nodes[right];
            }
        }

        // 루트 노드(맨 처음 노드)에 대한 연결 설정
        this.head = nodes[0];
    }

    // 순회
    // 전위 순회
    public void preOrder(Node node) {
        // 재귀함수 탈출 조건 -> 더 이상 노드가 없으면 탈출
        if(node == null) {
            return;
        }

        // pre-order이므로 현재 -> 좌측 -> 우측 순으로 탐색
        System.out.print(node.data + " ");
        this.preOrder(node.left);
        this.preOrder(node.right);
    }

    // 중위 순회
    public void inOrder(Node node) {
        // 재귀함수 탈출 조건 -> 더 이상 노드가 없으면 탈출
        if(node == null) {
            return;
        }

        // in-order이므로 좌측 -> 현재 -> 우측 순으로 탐색
        this.inOrder(node.left);
        System.out.print(node.data + " ");
        this.inOrder(node.right);
    }

    // 후위 순회
    public void postOrder(Node node) {
        // 재귀함수 탈출 조건 -> 더 이상 노드가 없으면 탈출
        if(node == null) {
            return;
        }

        // in-order이므로 좌측 -> 우측 -> 현재 순으로 탐색
        this.postOrder(node.left);
        this.postOrder(node.right);
        System.out.print(node.data + " ");
    }

    // 레벨 순회
    public void levelOrder(Node node) {
        // 큐를 이용하여 데이터를 넣은 순서대로 출력한다.
        // 큐에 노드를 넣음 -> 꺼냄 -> 링크 검사 후 좌측부터 노드가 있다면 모두를 큐에 넣음 -> 꺼냄 => 이를 반복
        Queue<Node> queue = new LinkedList<>();
        // 맨 처음 노드를 추가
        queue.add(node);

        // 큐가 빌 때 까지 loop 수행하며 순회
        while (!queue.isEmpty()) {
            // 노드 하나 꺼내고 출력
            Node cur = queue.poll();
            System.out.print(cur.data + " ");

            // 꺼낸 노드의 좌/우 링크 검사 => 자식 노드가 있으면 큐에 넣음
            if(cur.left != null) {
                queue.offer(cur.left);
            }
            if(cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }
}

public class Tree_Lecture2 {
    public static void main(String[] args) {
        // 트리 예제 코드
        // 배열이 들어오면 배열의 요소 그 순서대로 이진트리가 구성되었다고 가정
        char[] arr = new char[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ('A' + i); // 알파벳 A부터 총 10개의 데이터가 순서대로 배열에 들어감
        }

        BinaryTree2 bt2 = new BinaryTree2(arr);

        System.out.println("** PreOrder **");
        bt2.preOrder(bt2.head);
        System.out.println();

        System.out.println("** InOrder **");
        bt2.inOrder(bt2.head);
        System.out.println();

        System.out.println("** PostOrder **");
        bt2.postOrder(bt2.head);
        System.out.println();

        System.out.println("** LevelOrder **");
        bt2.levelOrder(bt2.head);
        System.out.println();
    }
}
