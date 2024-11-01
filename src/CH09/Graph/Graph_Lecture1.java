package CH09.Graph;

// 비선형 자료구조 - 그래프
// 인접 행렬을 이용한 그래프 구현

// 그래프로 사용할 인접 행렬
class MyGraphMatrix {
    // 노드의 정보는 알파벳으로 되어있으므로 char 변수 필요
    char [] vertices;
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
        if(isFull()) {
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

    // 만약 방향 그래프라면 한쪽만 업데이트 한다.
    public void addDirectEdge(int x, int y) {
        this.adjMat[x][y] = 1;
    }

    // 간선 정보 삭제 (양방향)
    public void deleteEdge(int x, int y) {
        this.adjMat[x][y] = 0;
        this.adjMat[y][x] = 0;
    }

    // 간선 정보 삭제 (단방향) - 역시 추가와 동일하게 한쪽만 지워준다.
    public void deleteDirectEdge(int x, int y) {
        this.adjMat[x][y] = 0;
    }

    // 인접 행렬 정보 출력
    public void printAdjacentMatrix() {
        System.out.print("  ");
        // 인접행렬 출력 전 정점이 어디인지에 대한 도움말 출력을 위한 for
        for (char item : this.vertices) {
            System.out.print(item + " ");
        }

        System.out.println();
        // 정점 개수만큼 해당 인접행렬 데이터 출력
        for (int i = 0; i < this.elemCnt; i++) {
            // 정점이 어디인지에 대한 도움말 출력을 위한 바깥 for
            System.out.print(this.vertices[i] + " ");
            for (int j = 0; j < elemCnt; j++) {
                System.out.print(this.adjMat[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class Graph_Lecture1 {
    public static void main(String[] args) {
        // 그래프 예제 코드
        // 그래프 : 정점과 간선으로 이루어진 자료구조
        MyGraphMatrix graph = new MyGraphMatrix(4);

        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.printAdjacentMatrix();

    }
}
