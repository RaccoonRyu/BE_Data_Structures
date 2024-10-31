package CH08.Tree.Practices;

// Practice1
// 종이 접기
// 종이를 반으로 접었을 때, 안으로 파인 부분은 0, 볼록 튀어나온 부분은 1이라고 하자.
// 종이를 접을때는 오른쪽에서 왼쪽으로 접는다.
// 종이를 N번 접었을 때의 접힌 상태를 출력하는 문제를 작성

// 입출력 예시
// 입력 : 1
// 출력 : 0

// 입력 : 2
// 출력 : 0, 0, 1

// 입력 : 3
// 출력 : 0, 0, 1, 0, 0, 1, 1

// idea : 접힌 부분을 Tree로 매핑 => 접었을 때 마다 부모를 기점으로 좌/우 0, 1이 반복되는 구조(포화 이진 트리) 0 -> 좌0/우1
// 이 때 출력이 0, 0, 1 순이므로 in-order로(좌-중-우) 탐색중임을 알 수 있다.
// 접힌 n번에 따라서 tree size만들고 해당 트리 데이터 내용 채운 다음 inorder 형태로 출력!

public class Tree_Practice1 {

    public static void solution(int n) {
        // 포화 이진 트리 이므로, 노드의 개수는 2의 n제곱 - 1 이어야 하나 가장 마지막 위치 사용하지 않겠음!
        int[] binaryTree = new int[(int)Math.pow(2, n)];

        // 한번 접으면 무조건 안으로 파이므로 처음은 0으로 초기화
        binaryTree[0] = 0;

        // 이후 loop 수행하며 좌우로 0-1 붙여주는 작업
        // 이 때 2의 n-1에 1을 빼주는 이유는 이미 맨 처음이 0으로 초기화 되므로, 좌 우 노드 하나씩만 붙여준다.
        for (int i = 0; i < (int)Math.pow(2, n - 1) - 1; i++) {
            // 트리의 왼쪽 부분
            binaryTree[i * 2 + 1] = 0;
            // 트리의 오른쪽 부분
            // 이렇게 하면 n 사이즈에 맞게 접힌 구조대로 0 또는 1에 대한 데이터가 쌓인다.
            binaryTree[i * 2 + 2] = 1;
        }

        // 중위 순회
        inOrder(binaryTree, 0);
        System.out.println();
    }

    // 중위 순회를 위한 함수
    public static void inOrder(int[] arr, int idx) {
        // in-order를 위한 idx값이 들어오면 left right를 먼저 구함
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        // in-order 구조로 재귀 호출하여 좌측 - 현재 - 우측 탐색
        // 배열의 마지막 부분은 사용하지 않기 때문에 arr.length - 1
        if(left < arr.length - 1) {
            inOrder(arr, left);
        }

        System.out.print(arr[idx] + " ");

        if(right < arr.length - 1) {
            inOrder(arr, right);
        }
    }

    public static void main(String[] args) {
        solution(1);
        solution(2);
        solution(3);
    }
}
