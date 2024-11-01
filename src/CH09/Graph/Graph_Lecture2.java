package CH09.Graph;

// 인접 리스트(연결 리스트)를 이용한 그래프 구현

// 연결리스트를 위한 Node
class Node {
    int id;
    Node next;

    public Node(int id, Node next) {
        this.id = id;
        this.next = next;
    }
}

class MyGraphList{
    char[] vertices;
    Node[] adjList; // 인접 리스트를 위한 Node 배열
    int elemCnt;

    public MyGraphList() {}
    public MyGraphList(int size) {
        this.vertices = new char[size];
        this.adjList = new Node[size];
        this.elemCnt = 0;
    }

    // 정점의 포화상태 확인하는 메서드
    public boolean isFull() {
        return this.elemCnt == this.vertices.length;
    }

    // 정점 추가하는 메서드
    public void addVertex(char data) {
        if(isFull()) {
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

    // 단방향 간선 추가
    public void addDirectEdge(int x, int y) {
        this.adjList[x] = new Node(y, this.adjList[x]);
    }
    
    // 인접 리스트 출력
    public void printAdjacentList() {
        for (int i = 0; i < this.elemCnt; i++) {
            // 정점에 대한 정보 출력
            System.out.print(this.vertices[i] + ": ");

            // 연결된 수 만큼 순회하며 출력
            Node cur = this.adjList[i];
            while (cur != null) {
                System.out.print(this.vertices[cur.id] + " ");
                cur = cur.next;
            }
            System.out.println();
        }
    }
}

public class Graph_Lecture2 {
    public static void main(String[] args) {
        MyGraphList graph = new MyGraphList(4);

        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.printAdjacentList();
    }
}
