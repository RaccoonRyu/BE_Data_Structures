package CH06.Heap.Practices;

// Practice2
// 최소 힙, 최대 힙을 이용하여 데이터를 오름차순, 내림차순으로 출력
// 최소 힙은 가장 작은 데이터가 각 노드를 비교하며 최상위 노드로 올라오기 때문에 오름차순,
// 최대 힙은 가장 큰 데이터가 각 노드를 비교하며 최상위 노드로 올라오기 때문에 내림차순에 사용하기 좋다.

import java.util.ArrayList;

// 최대 힙
class MaxHeap {
    ArrayList<Integer> heap;

    public MaxHeap() {
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

        // Max Heap -> 최댓값이 가장 최상위로 와야함 -> loop 수행하며 자식이 더 크면 위로 올려줌
        // 더미데이터로 0번에 데이터를 넣었고, 1보다는 커야지 위-아래에서 비교할 수 있기 때문에 cur은 1보다 커야하고
        // 동시에(AND) 자기 부모의 인덱스(cur/2)로 가져온 값이 자기 자신의 인덱스로 가져온 값 보다 작아야한다.
        while (cur > 1 && heap.get(cur / 2) < heap.get(cur)) {
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
                // 왼쪽 자식 노드의 값이 오른쪽 자식 노드의 값보다 크면 값 비교 대상은 왼쪽, 작으면 오른쪽
                targetIdx = heap.get(leftIdx) > heap.get(rightIdx) ? leftIdx : rightIdx;
            } else if (leftIdx < heap.size()) { // 자식노드가 하나인 경우 (왼쪽만 존재하는 경우)
                // targetIdx를 해당 자식노드가 될 수 있도록 바로 계산
                targetIdx = cur * 2; // (= leftIdx)
            } else { // 더 이상 비교할 대상이 없는 경우 (부모노드만 있거나 리프노드인 경우?)
                break;
            }

            // 위에서 수정한 targetIdx를 사용하여 부모값과 비교
            // 현재 위치의 값이 targetIdx 위치값보다 크면 문제 없으니 break
            if(heap.get(cur) > heap.get(targetIdx)) {
                break;
            } else { // 현재 위치의 값이 targetIdx 위치값보다 작으므로 targetIdx 위치값과 교환
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

public class Heap_Practice2 {
    // MinHeap을 파라미터로 사용하여 MaxHeap에 넣고 내림차순하는 함수
    public static void solution(MinHeap minHeap) {
        // 오름차순에 사용할 최대 힙 생성
        MaxHeap maxHeap = new MaxHeap();

        System.out.print("오름차순 : ");

        // 맨 앞 더미데이터만 남을 때까지 loop 수행하며 최대 힙에 데이터 추가
        while (minHeap.heap.size() != 1) {
            int data = minHeap.delete();
            System.out.print(data + " ");
            maxHeap.insert(data);
        }
        System.out.println();

        // 최대 힙에 추가한 데이터를 꺼내며 내림차순하여 표시
        System.out.print("내림차순 : ");
        while (maxHeap.heap.size() != 1) {
            // 출력만 하고 종료하므로 꺼내고 끝
            System.out.print(maxHeap.delete() + " ");
        }
        System.out.println();

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

        solution(minHeap);
    }
}
