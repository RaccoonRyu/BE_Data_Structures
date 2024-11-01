package CH09.Graph;

// 인접 리스트 그래프의 DFS, BFS

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 기 작성한 MyGraphList 상속받아 작성
class MyGraphList2 extends MyGraphList {
    public MyGraphList2(int size) {
        super(size);
    }

    // dfs - 방문 배열과 정점을 담을 stack 필요
    public void dfs(int id) {
        boolean[] visited = new boolean[this.elemCnt];
        Stack<Integer> stack = new Stack<>();

        stack.push(id);
        visited[id] = true;

        while (!stack.isEmpty()) {
            int curId = stack.pop();
            System.out.print(this.vertices[curId] + " ");

            // 인접 정점 확인 (인접 리스트 -> 연결 리스트 이므로 링크 따라가며 순회)
            // curId에 해당하는 Node(각 알파벳에 연결된 연결 리스트들의 head)를 cur에 담고 순회한다.
            Node cur = this.adjList[curId];
            // Node들을 순회하는 도중에는 방문 배열을 확인하고 미방문 시 stack에 추가한다.
            while(cur != null) {
                // 노드의 id에 해당하는 방문 배열이 false = 미방문
                if(visited[cur.id] == false) {
                    stack.push(cur.id);
                    visited[cur.id] = true;
                }
                // 다음 Node를 순회하기 위한 로직
                cur = cur.next;
            }
        }
        System.out.println();
    }

    // bfs - 방문 배열과 정점을 담을 queue 필요
    public void bfs(int id) {
        boolean[] visited = new boolean[this.elemCnt];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(id);
        visited[id] = true;

        while (!queue.isEmpty()) {
            int curId = queue.poll();
            System.out.print(this.vertices[curId] + " ");

            // 연결리스트 순회하며 탐색
            Node cur = this.adjList[curId];
            while (cur != null) {
                // Node들을 순회하는 도중에는 방문 배열을 확인하고 미방문 시 queue에 추가한다.
                if(visited[cur.id] == false) {
                    queue.offer(cur.id);
                    visited[cur.id] = true;
                }
                // 다음 Node를 순회하기 위한 로직
                cur = cur.next;
            }
        }
        System.out.println();
    }
}

public class Graph_Lecture4 {
    public static void main(String[] args) {
        MyGraphList2 graph = new MyGraphList2(7);

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
        graph.printAdjacentList();

        System.out.println();
        graph.dfs(0);
        graph.bfs(0);
    }
}
