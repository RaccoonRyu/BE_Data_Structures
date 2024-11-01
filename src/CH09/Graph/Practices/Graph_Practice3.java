package CH09.Graph.Practices;

// Practice3
// 주어진 그래프를 두 개의 그래프로 분리할 수 있는지 확인하는 프로그램 작성
// 분리 조건 : 인접하지 않은 노드끼리 분리 -> 한 노드가 있을 때, 해당 노드와 인접한 노드는 모두 다른 그룹에 있어야 함
// 모든 노드는 연결되어있고, 분리 가능하면 true/불가능하면 false 출력
// 간선 정보 : 0번 노트부터 n번 노드까지 연결된 간선들의 정보 (ex. 예시 1의 0번 노드는 1, 3번 노드와 연결)

// 입력 예시
// 그래프 : {{1, 3}, {0, 2}, {1, 3}, {0, 2}}
// 출력 : true

// 그래프 : {{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}
// 출력 : false

// idea : 인접 여부를 확인하면 분리 가능여부 또한 확인 가능 -> 각 노드의 인접한 노드에 반대 부호(1 or -1)로 flag를 부여하여 인접 여부 check

public class Graph_Practice3 {

    public static void solution(int[][] graph) {
        // 각 노드들에 대한 flag를 1 or -1로 체크하기 위한 변수 배열
        // graph 배열이 간선 정보(node 수 - 1)가 아니고 노드 개수만큼의 정보이기 때문에 바로 length
        int[] flags = new int[graph.length];

        // 비교하며 flags 채우는 함수 호출
        // flag는 1로, 방문하는 노드 위치는 처음 노드 0
        if(checkSplit(graph, flags, 1, 0) == true) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    // 인접여부 비교하며 flags 채우는 함수
    // flag : 현재 설정해줘야 할 flag 값, node : 현재 방문하는 노드 위치
    public static boolean checkSplit(int[][] graph, int[] flags, int flag, int node) {
        // 재귀 호출 중에 이상이 생겼을 때 탈출
        // flags[node] != 0 : 이미 방문해서 flags[node] 값이 1 or -1으로 체크된 상태
        // 코드가 여기까지 진행되었다는 것은 다른 순회 과정에서 이미 방문한 노드를 다시 방문했다는 의미
        // 이 때, flags[node]와 현재 할당하려는 flag 값이 다르면 인접한 노드끼리 같은 그룹에 있음을 의미하므로, 분리가 불가능하여 false를 반환하고 탈출(재귀 종료)
        if(flags[node] != 0) {
            return flags[node] == flag;
        }

        // 함수 호출 시 node 해당 위치의 flag를 넘어온 flag값으로 설정
        flags[node] = flag;

        // loop 수행하며 현재 노드와 연결되어있는(인접해있는) 노드들을 하나씩 꺼내서 checkSplit 재귀 호출
        for(int adjacentNode : graph[node]) {
            // 재귀 호출하여 인접한 노드를 방문할 때 flag를 반대 부호로 설정하고, 방문할 노드를 넘겨준다.
            // 만약 이 재귀 호출이 false를 return한다면, 아래에서도 false를 반환한다.
            if(!checkSplit(graph, flags, -flag, adjacentNode)) {
                return false;
            }
        }
        // 분리가 가능하다면 true 반환
        return true;
    }

    public static void main(String[] args) {
        // 0번 노드가 1, 3번과 인접 / 1번 노드가 0, 2번과 인접 ...
        // 따라서 그래프 생성 없이 바로 사용이 가능하다.
        int[][] graph = {{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        solution(graph);

        graph = new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        solution(graph);
    }
}
