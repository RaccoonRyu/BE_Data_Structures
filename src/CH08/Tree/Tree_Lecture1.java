package CH08.Tree;

// 배열을 이용한 이진트리 구성 및 순회

class BinaryTree {
    char[] arr;

    // 생성자 - 들어온 배열 데이터를 clone 하여 배열 생성
    BinaryTree(char[] data) {
        this.arr = data.clone();
    }

    // 순회 => order에 따라서 출력 위치를 신경써야한다.
    // 전위 순회 (pre-order) : 현재 -> 왼쪽 -> 오른쪽 순으로 탐색
    public void preOrder(int idx) {
        // 처음에 들어온 index 데이터 출력
        System.out.print(this.arr[idx] + " ");

        // 현재 출력한 대상의 left / right child를 구함
        // 인덱스가 0부터 시작하므로 아래와 같이 구한다.
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        // 좌/우 인덱스가 배열의 범위 안에 있다면 해당 child는 존재함을 의미하므로 재귀함수 형태로 preOrder 호출
        if(left < this.arr.length) {
            this.preOrder(left);
        }

        if(right < this.arr.length) {
            this.preOrder(right);
        }
    }

    // 중위 순회(in-order) : 왼쪽 -> 현재 -> 오른쪽 순으로 탐색
    public void inOrder(int idx) {
        // 함수 호출 시 인덱스 먼저 구함
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        // 좌/우 인덱스가 배열의 범위 안에 있다면 해당 child는 존재함을 의미하므로 재귀함수 형태로 inOrder 호출하고 출력
        // 왼쪽으로 최대한 내려간 후 출력한다.
        if(left < this.arr.length) {
            this.inOrder(left);
        }

        System.out.print(this.arr[idx] + " ");

        if(right < this.arr.length) {
            this.inOrder(right);
        }
    }

    // 후위 순회(post-order) : 왼쪽 - 오른쪽 - 현재 순으로 탐색
    public void postOrder(int idx) {
        // 함수 호출 시 인덱스 먼저 구함
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        // 좌/우 인덱스가 배열의 범위 안에 있다면 해당 child는 존재함을 의미하므로 재귀함수 형태로 postOrder 호출하고 출력
        if(left < this.arr.length) {
            this.postOrder(left);
        }

        // 오른쪽으로 최대한 내려간 후 출력한다.
        if(right < this.arr.length) {
            this.postOrder(right);
        }

        System.out.print(this.arr[idx] + " ");
    }

    // 레벨 순회(level-order) : 트리의 레벨별로 탐색
    public void levelOrder(int idx) {
        // 배열로 구성한 레벨 오더는 간단히 for문 수행하며 순서대로 출력
        for (int i = 0; i < this.arr.length; i++) {
            System.out.print(this.arr[i] + " ");
        }
    }
}

public class Tree_Lecture1 {
    public static void main(String[] args) {
        // 트리 예제 코드
        // 이진 트리

        // 배열이 들어오면 배열의 요소 그 순서대로 이진트리가 구성되었다고 가정
        char[] arr = new char[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) ('A' + i); // 알파벳 A부터 총 10개의 데이터가 순서대로 배열에 들어감
        }

        BinaryTree bt = new BinaryTree(arr);

        System.out.println("** PreOrder **");
        bt.preOrder(0);
        System.out.println();

        System.out.println("** InOrder **");
        bt.inOrder(0);
        System.out.println();

        System.out.println("** PostOrder **");
        bt.postOrder(0);
        System.out.println();

        System.out.println("** LevelOrder **");
        bt.levelOrder(0);
        System.out.println();
    }
}
