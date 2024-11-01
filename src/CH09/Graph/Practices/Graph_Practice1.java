package CH09.Graph.Practices;

// Practice1
// Center Node 찾기
// Undirected 그래프(무방향 그래프)에서 center node를 출력
// Center node : 다른 모든 노드와 연결된 노드
// 다른 노드와 연결된 센터 노드는 하나라고 가정

// 입력 : {{1, 2}, {2, 3}, {4, 2}}
// 출력 : 2

// 입력 : {{1, 2}, {5, 1}, {1, 3}, {1, 4}}
// 출력 : 1

// solution1 idea : 센터 노드의 간선 수가 제일 많다 -> 간선의 개수가 가장 많은 노드를 출력하면 된다. or 노드 개수 - 1만큼의 간선을 가진 노드를 출력하면 된다.

// solution2 idea : 간선의 총 개수는 노드의 개수 - 1 / 모든 노드는 연결되어 있다는 제약 조건이 있을 때 간선의 정보를 보면 특정 노드가 어느 간선의 정보에나 모두 존재함
// 이 정보를 이용하여 각 간선의 맨 처음 정보를 다음 간선의 모든 정보와 비교해서 중복되는 노드가 있다면 맨 처음 정보의 좌측이 센터, 없다면 우측이 센터 노드라고 판단할 수 있다.

// 인접 행렬로 구성한 그래프
class MyGraphMatrix {
    // 노드의 정보는 알파벳으로 되어있으므로 char 변수 필요
    char[] vertices;
    // 간선을 관리할 행렬
    int[][] adjMat;
    // 실제 그래프의 정점 개수
    int elemCnt;

    public MyGraphMatrix() {
    }

    public MyGraphMatrix(int size) {
        this.vertices = new char[size];
        this.adjMat = new int[size][size];
        this.elemCnt = 0;
    }

    // 정점의 포화상태 확인하는 메서드
    public boolean isFull() {
        return this.elemCnt == this.vertices.length;
    }

    // 새 알파벳에 대한 정점 추가 메서드
    public void addVertex(char data) {
        if (isFull()) {
            System.out.println("Graph is Full!");
            return;
        }

        // 정점 개수 변수를 이용하여 알파벳 데이터를 넣어 노드 생성
        this.vertices[this.elemCnt++] = data;
    }

    // 정점 간선 정보를 인접행렬에 구성(추가) - 간선과 노드를 연결
    public void addEdge(int x, int y) {
        // 무방향 그래프의 간선은 양방향으로 하나의 정점과 다른 하나의 정점을 이으므로 x-y, y-x 모두 1을 추가해준다.
        this.adjMat[x][y] = 1;
        this.adjMat[y][x] = 1;
    }
}

public class Graph_Practice1 {

    public static int solution1(int[][] e) {
        // 들어오는 데이터를 기반으로 그래프 구성
        // edges는 노드간에 연결된 간선 정보만을 표현해주기 때문에 노드 수 - 1만큼으로 구성되어 있음
        // 따라서 그래프는 노드수 만큼 만들어주기 위해 e의 길이 + 1을 시켜 생성
        MyGraphMatrix graph = new MyGraphMatrix(e.length + 1);

        // loop 수행하며 간선 정보 추가
        for (int i = 0; i < e.length; i++) {
            // 아래 edges 정보에서는 노드가 1부터 시작이므로, 인덱싱 편의를 위해 - 1 하여 0을 기본으로 들어가도록 생성한다.
            graph.addEdge(e[i][0] - 1, e[i][1] - 1);
        }

        // 각 노드들에 대한 edge 계산
        int[] edgeCnt = new int[e.length + 1]; // edgeCnt의 인덱스가 노드, 안의 값은 노드에 해당하는 간선 수
        // 인접 행렬의 길이만큼 loop 수행하여 인접 행렬 내 각 노드에 1이 된 부분 체크하여 count
        for (int i = 0; i < graph.adjMat.length; i++) {
            for (int j = 0; j < graph.adjMat[i].length; j++) {
                // i, j 인덱스에 해당하는 인접 행렬 값이 1이라면 해당 부분의 edgeCnt 증가
                if(graph.adjMat[i][j] == 1) {
                    edgeCnt[i] += 1;
                }
            }
        }

        // 간선의 최댓값과 해당 노드 찾기
        int maxCnt = -1;
        int maxIdx = -1;
        for (int i = 0; i < edgeCnt.length; i++) {
            if(maxCnt < edgeCnt[i]) {
                maxCnt = edgeCnt[i];
                maxIdx = i;
            }
        }

        // 위에서 인덱싱 편의를 위해 1을 빼서 계산했으므로, 다시 1을 더하여 노드를 반환
        return maxIdx + 1;
    }

    // 간선 정보가 배열로 넘어올 때 0-0번째와 1-0번째를 비교하거나(OR) 0-0번째와 1-1번째를 비교해서 true면 0-0이, false면 0-1이 센터노드이다.
    public static int solution2(int[][] e) {
        return e[0][0] == e[1][0] || e[0][0] == e[1][1] ? e[0][0] : e[0][1];
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 2}, {2, 3}, {4, 2}};
        System.out.println(solution1(edges));
        System.out.println(solution2(edges));
        System.out.println();

        edges = new int[][]{{1, 2}, {5, 1}, {1, 3}, {1, 4}};
        System.out.println(solution1(edges));
        System.out.println(solution2(edges));
        System.out.println();

    }
}
