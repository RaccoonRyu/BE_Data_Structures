package CH08.Tree.Practices;

// Practice2
// 각각의 에지(간선)에 가중치가 있는 포화 이진트리가 있다.
// 루트에서 각 리프까지의 경로 길이를 모두 같게 설정하고
// 이 때 모든 가중치들의 총합이 최소가 되도록 하는 프로그램을 작성하시오.

// idea : 루트에서 리프까지의 경로에 있는 에지 가중치 합을 같게 만들어줘야함 => 최소가 되려면 상위 노드 에지의 가중치를 늘려줘야 아래 노드 에지로 전파되며 합이 가능
// 따라서 같은 레벨의 데이터를 맞춰준 다음, 바로 상위 노드 에지의 가중치를 조절한다.

//
class BinaryTree {
    int h; // 높이
    int[] arr; // 가중치 배열
    int res; // 가중치 총합 결과

    public BinaryTree(int h, int[] w) {
        this.h = h;
        arr = new int[(int)Math.pow(2, h + 1)]; // 2의 h+1제곱만큼 트리용 배열을 만들고 맨 뒤 위치는 사용하지 않는다.
        res = 0;

        // 노드가 아닌 간선에 가중치를 부여할 것이므로, 가중치 배열의 인덱스는 2부터 시작한다. (ex 포화 이진 트리의 높이가 2일때 노드의 개수는 7개, 간선의 개수는 6개 이므로)
        for (int i = 2; i < (int)Math.pow(2, h + 1); i++) {
            arr[i] = w[i - 2];
        }
    }

    // 형제 노드들의 레벨을 맞추는 작업 수행
    public int dfs(int idx, int h) {
        // 현재 높이가 트리의 최대 높이와 같아진 경우, leaf 노드에 도달한 상태
        if(this.h == h) {
            res += arr[idx]; // leaf 노드까지의 가중치를 더함
            return arr[idx]; // leaf 노드의 가중치를 반환하며 재귀를 탈출
        }

        // 현재 간선의 왼쪽 및 오른쪽 하위 간선을 재귀적으로 탐색하여 각 서브트리의 누적 가중치를 얻음
        int left = dfs(idx * 2, h + 1);
        int right = dfs(idx * 2 + 1, h + 1);

        // 현재 간선의 가중치에 좌우 서브트리 간의 가중치 차이를 더하여 누적 가중치를 조정
        res += arr[idx] + Math.abs(left - right);
        // 부모 간선으로 전달할 값은 현재 간선의 가중치와 좌우 서브트리 중 큰 값의 합
        return arr[idx] + Math.max(left, right);
    }
}

public class Tree_Practice2 {
    public static void solution(int h, int[] w) {
        // 이진트리 생성
        BinaryTree bt = new BinaryTree(h, w);

        // idx = 1, 높이 = 0으로 dfs 호출
        bt.dfs(1, 0);

        // 나온 결과 출력
        System.out.println(bt.res);
    }

    public static void main(String[] args) {
        int h = 2; // 트리의 높이
        int[] w = {2, 2, 2, 1, 1, 3}; // 트리에서 각 간선의 가중치
        solution(h, w);

        /*h = 3;
        w = new int[]{1, 2, 1, 3, 2, 4, 1, 1, 1, 1, 1, 1, 1, 1};
        solution(h, w);*/

    }
}
