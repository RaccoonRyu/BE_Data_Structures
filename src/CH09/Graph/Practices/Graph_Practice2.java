package CH09.Graph.Practices;

// Practice2
// 주어진 그래프에서 시작 노드부터 끝 노드로 가는 길이 있는지 확인하는 프로그램 작성
// path가 존재하면 true, 없으면 false 출력

// 입력 예시
// 노드 개수 = 3
// 간선 정보 = {{0, 1}, {1, 2}, {2, 0}}
// 출발 노드 = 0
// 종착 노드 = 2
// 출력 : true

// idea : dfs로 특정 지점을 잡으면 연결되어 있는 부분은 모두 한번씩 방문이 되기 때문에, 방문한 list를 저장했다가 시작 지점과 끝 지점이 둘 다 있다면 true!

import java.util.ArrayList;
import java.util.Stack;

// 연결리스트를 위한 Node
class Node {
    int id;
    Node next;

    public Node(int id, Node next) {
        this.id = id;
        this.next = next;
    }
}

class MyGraphList {
    int[] vertices;
    Node[] adjList; // 인접 리스트를 위한 Node 배열
    int elemCnt;

    public MyGraphList() {
    }

    public MyGraphList(int size) {
        this.vertices = new int[size];
        this.adjList = new Node[size];
        this.elemCnt = 0;
    }

    // 정점의 포화상태 확인하는 메서드
    public boolean isFull() {
        return this.elemCnt == this.vertices.length;
    }

    // 정점 추가하는 메서드
    public void addVertex(int data) {
        if (isFull()) {
            System.out.println("Graph is full!");
            return;
        }
        this.vertices[elemCnt++] = data;
    }

    // 양방향 간선 추가
    public void addEdge(int x, int y) {
        // 이미 가리키고 있는 간선 정보가 있으면 모든 노드를 순회할 필요 없이 새 노드를 기 존재하는 노드의 앞에 항상 추가함
        this.adjList[x] = new Node(y, this.adjList[x]);
        this.adjList[y] = new Node(x, this.adjList[y]);
    }
}

public class Graph_Practice2 {
    // 노드 개수, 간선 정보 배열, 출발지, 도착지
    public static void solution(int n, int[][] edges, int source, int dest) {
        // 인접 리스트 방식의 그래프 생성 -> 노드 개수 넣어서 생성
        MyGraphList graph = new MyGraphList(n);

        // 노드 정보 추가
        for (int i = 0; i < n; i++) {
            graph.addVertex(i);
        }
        
        // 간선 정보 추가
        for (int i = 0; i < edges.length; i++) {
            graph.addEdge(edges[i][0], edges[i][1]);
        }

        // dfs로 그래프를 순회하면서 방문했던 노드들을 List에 추가
        ArrayList<Integer> visitedItem = new ArrayList<>();
        dfs(graph, 0, visitedItem);

        // 출발-도착지점이 list에 들어있는지 확인 후 있으면 true, 없으면 false 반환
        if(visitedItem.contains(source) && visitedItem.contains(dest)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }

    // dfs 수행하는 메서드
    public static void dfs(MyGraphList graph, int id, ArrayList<Integer> visitedItem) {
        // 방문 배열과 stack 이용하여 dfs를 위한 변수 생성
        boolean visited[] = new boolean[graph.vertices.length];
        Stack<Integer> stack = new Stack<>();

        // id 데이터 push 및 id 인덱스 방문배열 갱신
        stack.push(id);
        visited[id] = true;

        while (!stack.isEmpty()) {
            int curId = stack.pop();
            // 방문한 노드를 list에 추가
            visitedItem.add(curId);

            // loop 수행하며 인접 리스트에 연결된 노드들 탐색
            Node cur = graph.adjList[curId];
            while (cur != null) {
                if(visited[cur.id] == false) {
                    stack.push(cur.id);
                    visited[cur.id] = true;
                }
                cur = cur.next;
            }
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        int source = 0;
        int dest = 2;
        solution(n, edges, source, dest);

        n = 6;
        edges = new int[][]{{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        source = 0;
        dest = 5;
        solution(n, edges, source, dest);
    }
}
