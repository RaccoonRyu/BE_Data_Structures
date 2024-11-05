package CH06.Heap.Practices;

// Practice1
// 최소 힙에서 특정 값을 변경하는 코드를 작성
// 특정 값이 여러 개라면 모두 바꾼다.

// idea : 특정 값이 바뀌었을 때 최소 힙 구조에 적합하도록 데이터 위치가 조정되어야 함.

import java.util.ArrayList;

class MinHeap {
    ArrayList<Integer> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
        // 더미 데이터를 넣어서 인덱스를 1부터 시작할 수 있도록 함
        this.heap.add(0);
    }

    // 데이터 삽입
    public void insert(int data) {
        // 데이터가 맨 처음에 들어오면 자료구조의 가장 맨 끝에 넣어준다.
        heap.add(data);

        // 데이터를 넣은 다음 부모와 값을 비교하여 부모가 더 크면 자기 자신을 부모 자리로 올린다.
        // 방금 넣은 가장 끝 데이터의 index
        int cur = heap.size() - 1;

        // Min Heap -> 최솟값이 가장 최상위로 와야함 -> loop 수행하며 자식이 더 작으면 위로 올려줌
        // 더미데이터로 0번에 데이터를 넣었고, 1보다는 커야지 위-아래에서 비교할 수 있기 때문에 cur은 1보다 커야하고
        // 동시에(AND) 자기 부모의 인덱스(cur/2)로 가져온 값이 자기 자신의 인덱스로 가져온 값 보다 커야한다.
        while (cur > 1 && heap.get(cur / 2) > heap.get(cur)) {
            // 부모쪽 현재 데이터를 임시저장하고
            int parentVal = heap.get(cur / 2);
            // 부모쪽에는 방금 들어온 더 작은 데이터를 넘겨줌
            heap.set(cur / 2, data);
            // 자식쪽에는 임시저장한 부모쪽 데이터를 넣어줌
            heap.set(cur, parentVal);

            // 바교한 노드보다 더 상위 노드와의 비교를 위해 cur을 2로 나눈다.
            cur /= 2;
        }
    }

    // 가장 위의 데이터 삭제 및 반환
    public Integer delete() {
        // 삭제를 위한 size check -> 사이즈가 1이면 더미데이터만 존재함을 의미
        if(heap.size() == 1) {
            System.out.println("Heap is empty!");
            return null;
        }

        // 삭제는 가장 최상위 노드 데이터를 꺼내 삭제하고 반환한다. 최상위 노는 1번 인덱스의 데이터이다.
        int target = heap.get(1);

        // 이후 가장 마지막 노드의 데이터를 가장 최상위 노드로 가져와 올리고 삭제한다.
        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        // 가장 위로 올린 데이터가 최소 힙 기준을 부합하는지 loop를 통해 자식노드들과 비교하며 값을 조정한다.
        // 현재 위치는 최상위 노드의 위치이므로 1
        int cur = 1;
        while (true) {
            // 왼쪽/오른쪽 자식 노드의 idx
            int leftIdx = cur * 2;
            int rightIdx = cur * 2 + 1;

            // 값 비교 대상을 정하기 위한 idx
            int targetIdx = -1;

            // 자식노드들과의 비교
            // 우측 자식 노드의 인덱스가 heap의 크기보다 작아야 해당 노드가 존재한다는 의미 -> 왼쪽 오른쪽 자식 노드가 둘 다 존재
            if(rightIdx < heap.size()) {
                // 왼쪽 자식 노드의 값이 오른쪽 자식 노드의 값보다 작으면 값 비교 대상은 왼쪽, 크면 오른쪽
                targetIdx = heap.get(leftIdx) < heap.get(rightIdx) ? leftIdx : rightIdx;
            } else if (leftIdx < heap.size()) { // 자식노드가 하나인 경우 (왼쪽만 존재하는 경우)
                // targetIdx를 해당 자식노드가 될 수 있도록 바로 계산
                targetIdx = cur * 2; // (= leftIdx)
            } else { // 더 이상 비교할 대상이 없는 경우 (부모노드만 있거나 리프노드인 경우?)
                break;
            }

            // 위에서 수정한 targetIdx를 사용하여 부모값과 비교
            // 현재 위치의 값이 targetIdx 위치값보다 작으면 문제 없으니 break
            if(heap.get(cur) < heap.get(targetIdx)) {
                break;
            } else { // 현재 위치의 값이 targetIdx 위치값보다 크므로 targetIdx 위치값과 교환
                int parentVal = heap.get(cur);
                heap.set(cur, heap.get(targetIdx));
                heap.set(targetIdx, parentVal);

                // 교환 이후 교환한 위치의 자식 노드들과 값 비교해야할 수 있으므로 cur을 교환 위치로 조정
                cur = targetIdx;
            }

        }

        // 가장 처음에 꺼낸 값 반환
        return target;
    }

    // 출력
    public void printTree() {
        // 0번째 위치는 현재 들어있는 더미데이터기 때문에 1부터 시작
        for (int i = 1; i < this.heap.size(); i++) {
            System.out.print(this.heap.get(i) + " ");
        }
        System.out.println();
    }
}

public class Heap_Practice1 {

    // from : 변경할 특정 값, to : 특정 값 대신 넣을 새로운 값
    public static void solution(MinHeap minHeap, int from, int to) {
        // heap의 크기만큼 loop 수행하며
        for (int i = 0; i < minHeap.heap.size(); i++) {
            // minHeap의 i번째 값이 변경할 특정 값과 같을 때 i번째 값을 새로운 값으로 변경
            if(minHeap.heap.get(i) == from) {
                minHeap.heap.set(i, to);

                // 변경이 일어났을 때마다 최소 힙 구조에 따라서 올리거나 내리는 작업하는 함수를 호출한다.
                moveUp(minHeap, i);
                moveDown(minHeap, i);
            }
        }
    }

    // 변경이 일어났을 때 최소 힙 구조에 따라서 올리는 함수 (부모 노드 값 > 현재 노드 값)
    // idx : 현재 새로운 값으로 변경된 해당 노드 위치
    public static void moveUp(MinHeap minHeap, int idx) {
        int cur = idx;

        // cur이 1보다 크고, 부모 인덱스에 위치하는 값보다 현재 인덱스에 위치하는 값이 작아야 위로 올릴 수 있다.
        while (cur > 1 && minHeap.heap.get(cur / 2) > minHeap.heap.get(cur)) {
            int parentVal = minHeap.heap.get(cur / 2);
            minHeap.heap.set(cur / 2, minHeap.heap.get(cur));
            minHeap.heap.set(cur, parentVal);

            // 더 상위 노드와의 값 비교를 위해 cur을 2로 나눈다.
            cur /= 2;
        }
    }

    // 변경이 일어났을 때 최소 힙 구조에 따라서 내리는 함수 (현재 노드 값 > 자식 노드 값)
    public static void moveDown(MinHeap minHeap, int idx) {
        int cur = idx;

        // loop 수행하며 자식 노드와 비교하여 값을 내려줌
        while (true) {
            int leftIdx = cur * 2;
            int rightIdx = cur * 2 + 1;

            // 현재 위치에 있는 노드와 바꿀 대상의 위치를 설정하는 변수
            int targetIdx = -1;

            if(rightIdx < minHeap.heap.size()) { // 자식 노드가 좌/우 둘 다 있음
                targetIdx = minHeap.heap.get(leftIdx) < minHeap.heap.get(rightIdx) ? leftIdx : rightIdx;
            } else if (leftIdx < minHeap.heap.size()) { // 왼쪽 자식 노드만 있음
                targetIdx = leftIdx;
            } else { // 자식 노드가 없는 경우 탈출
                break;
            }

            // 현재 인덱스에 해당하는 값이 targetIdx에 해당하는 값보다 작으면 내릴 필요 없이 탈출
            if(minHeap.heap.get(cur) < minHeap.heap.get(targetIdx)) {
                break;
            } else { // 값의 변경이 필요할 경우 swap
                int parentVal = minHeap.heap.get(cur);
                minHeap.heap.set(cur, minHeap.heap.get(targetIdx));
                minHeap.heap.set(targetIdx, parentVal);

                // 교환 이후 교환한 위치의 자식 노드들과 값 비교해야할 수 있으므로 cur을 교환 위치로 조정
                cur = targetIdx;
            }
        }
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(30);
        minHeap.insert(40);
        minHeap.insert(10);
        minHeap.insert(50);
        minHeap.insert(60);
        minHeap.insert(70);
        minHeap.insert(20);
        minHeap.insert(30);
        System.out.println("** 데이터 변경 전 **");
        minHeap.printTree(); // 10 30 20 40 60 70 30 50

        System.out.println("** 데이터 변경 후 **");
        solution(minHeap, 30, 100);
        minHeap.printTree(); // 10 40 20 50 60 70 100 100

        solution(minHeap, 60, 1);
        minHeap.printTree(); // 1 10 20 50 40 70 100 100
    }
}
