package CH09.Graph;

// 인접 행렬 그래프의 DFS, BFS

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 기 작성한 MyGraphMatrix 상속받아 작성
class MyGraphMatrix2 extends MyGraphMatrix {

    public MyGraphMatrix2(int size) {
        super(size);
    }

    // dfs - 해당 노드의 방문 여부를 확인하기 위한 배열 하나와 stack 필요
    public void dfs(int id) {
        // 실제 정점의 개수 만큼 방문배열 생성
        boolean[] visited = new boolean[this.elemCnt];
        // 순회를 시작할 정점을 담기위한 스택 생성
        Stack<Integer> stack = new Stack<>();

        // 순회를 시작하려는 id를 stack에 push하고 해당 id의 방문배열 check
        stack.push(id);
        visited[id] = true;

        // 스택에서 하나씩 꺼내고 인접정점 확인 후 방문한적이 없다면 다시 넣는 과정 반복
        while (!stack.isEmpty()) {
            int curId = stack.pop();
            System.out.print(this.vertices[curId] + " "); // 꺼내고 curId에 해당하는 노드 이름 출력

            // 교안 순서대로(A-B-E-G-F-C-D 순) 탐색을 위해 역순으로 인접 정점 확인 (인접 행렬)
            for (int i = this.elemCnt - 1; i >= 0 ; i--) {
                // 인접 행렬의 해당 위치 값이 1이면 간선이 존재한다는 뜻이고, 방문 여부를 체크한 다음 해당 노드를 스택에 추가
                if(this.adjMat[curId][i] == 1 && visited[i] == false) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println();
    }

    // bfs - 해당 노드의 방문 여부를 확인하기 위한 배열 하나와 queue 필요
    public void bfs(int id) {
        // 실제 정점의 개수 만큼 방문배열 생성
        boolean[] visited = new boolean[this.elemCnt];
        // 순회를 시작할 정점을 담기위한 큐 생성
        Queue<Integer> queue = new LinkedList<>();

        // 순회를 시작하려는 id를 queue에 offer하고 해당 id의 방문배열 check
        queue.offer(id);
        visited[id] = true;

        // 큐에서 하나씩 꺼내고 인접정점 확인 후 방문한적이 없다면 다시 넣는 과정 반복
        while (!queue.isEmpty()) {
            int curId = queue.poll();
            System.out.print(this.vertices[curId] + " "); // 꺼내고 curId에 해당하는 노드 이름 출력

            // 역순으로 인접 정점 확인 (인접 행렬)
            for (int i = this.elemCnt - 1; i >= 0; i--) {
                // 인접 행렬의 해당 위치 값이 1이면 간선이 존재한다는 뜻이고, 방문 여부를 체크한 다음 해당 노드를 큐에 추가
                if (this.adjMat[curId][i] == 1 && visited[i] == false) {
                    queue.offer(i);
                    visited[i] = true;
                    // dfs와 차이점 : 자료구조가 stack에서 queue로 바뀌어 먼저 넣었던 노드가 먼저 나와 bfs로 동작
                }
            }
        }
        System.out.println();
    }
}

public class Graph_Lecture3 {
    public static void main(String[] args) {
        MyGraphMatrix2 graph = new MyGraphMatrix2(7);

        graph.addVertex('A'); // curId 0
        graph.addVertex('B'); // 1
        graph.addVertex('C'); // 2
        graph.addVertex('D'); // 3
        graph.addVertex('E'); // 4
        graph.addVertex('F'); // 5
        graph.addVertex('G'); // 6

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);
        graph.printAdjacentMatrix();

        System.out.println();
        graph.dfs(0);
        graph.bfs(0);

    }
}
