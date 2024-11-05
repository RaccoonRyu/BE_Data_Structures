package CH06.Heap.Practices;

// Practice3
// 정수들을 힙 자료구조에 추가하고 n번 삭제 후 절대값이 큰 값부터 출력하세요

// 입력 : 3 0 -2 -5 9 6 -11 20 -30
// 삭제 횟수 : 1
// 출력 : 20 -11 9 6 -5 3 -2 0

// idea : 절대값 기준으로 큰 값 부터 -> 각 노드를 절대값으로 비교하고, 자료구조로는 Max Heap을 사용하자.

import java.util.ArrayList;
import java.util.stream.IntStream;

// 절대값 비교를 위한 class
class Num {
    // 절대값을 담을 변수
    int val;
    // 값이 음수였다면 체크할 boolean 변수
    boolean isMinus;

    // 생성자 - 값을 입력 받았을 때 음수라면 isMinus가 true로 변경되도록 하고, val은 절대값을 할당
    public Num(int val) {
        this.isMinus = val < 0 ? true : false;
        this.val = Math.abs(val);
    }
}

// Max Heap에 Num 형태 데이터를 넣어줘야 하므로 기존에 사용한 MaxHeap 가져와 일부 변경
class MaxHeap2 {
    //
    ArrayList<Num> heap;

    public MaxHeap2() {
        this.heap = new ArrayList<>();
        // 더미 데이터를 넣어서 인덱스를 1부터 시작할 수 있도록 함
        this.heap.add(new Num(0));
    }

    // 데이터 삽입
    public void insert(int data) {
        // 데이터가 맨 처음에 들어오면 자료구조의 가장 맨 끝에 넣어준다.
        heap.add(new Num(data));

        // 데이터를 넣은 다음 부모와 값을 비교하여 부모가 더 크면 자기 자신을 부모 자리로 올린다.
        // 방금 넣은 가장 끝 데이터의 index
        int cur = heap.size() - 1;

        // Max Heap -> 최댓값이 가장 최상위로 와야함 -> loop 수행하며 자식이 더 크면 위로 올려줌
        // 더미데이터로 0번에 데이터를 넣었고, 1보다는 커야지 위-아래에서 비교할 수 있기 때문에 cur은 1보다 커야하고
        // 동시에(AND) 자기 부모의 인덱스(cur/2)로 가져온 값이 자기 자신의 인덱스로 가져온 값 보다 작아야한다.
        // 기존 Heap에서는 Integer를 사용했는데, Num으로 변경했으므로 .val을 추가해준다.
        while (cur > 1 && heap.get(cur / 2).val < heap.get(cur).val) {
            // 부모쪽 현재 데이터를 임시저장하고 (이때 단순 값이 아니라 해당 Num을 넘겨줘야 하므로 Num으로 변경)
            Num parentVal = heap.get(cur / 2);
            // 부모쪽에는 방금 들어온 더 큰 데이터를 넘겨줌
            heap.set(cur / 2, heap.get(cur));
            // 자식쪽에는 임시저장한 부모쪽 데이터를 넣어줌
            heap.set(cur, parentVal);

            // 바교한 노드보다 더 상위 노드와의 비교를 위해 cur을 2로 나눈다.
            cur /= 2;
        }
    }

    // 가장 위의 데이터 삭제 및 반환
    // 기존의 Integer를 모두 Num으로 변경하고, 값을 비교하는 부분에서는 .val을 추가해 Num class가 가진 값을 비교하도록 한다.
    public Num delete() {
        // 삭제를 위한 size check -> 사이즈가 1이면 더미데이터만 존재함을 의미
        if(heap.size() == 1) {
            System.out.println("Heap is empty!");
            return null;
        }

        // 삭제는 가장 최상위 노드 데이터를 꺼내 삭제하고 반환한다. 최상위 노는 1번 인덱스의 데이터이다.
        Num target = heap.get(1);

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
                targetIdx = heap.get(leftIdx).val > heap.get(rightIdx).val ? leftIdx : rightIdx;
            } else if (leftIdx < heap.size()) { // 자식노드가 하나인 경우 (왼쪽만 존재하는 경우)
                // targetIdx를 해당 자식노드가 될 수 있도록 바로 계산
                targetIdx = cur * 2; // (= leftIdx)
            } else { // 더 이상 비교할 대상이 없는 경우 (부모노드만 있거나 리프노드인 경우?)
                break;
            }

            // 위에서 수정한 targetIdx를 사용하여 부모값과 비교
            // 현재 위치의 값이 targetIdx 위치값보다 크면 문제 없으니 break
            if(heap.get(cur).val > heap.get(targetIdx).val) {
                break;
            } else { // 현재 위치의 값이 targetIdx 위치값보다 작으므로 targetIdx 위치값과 교환
                Num parentVal = heap.get(cur);
                heap.set(cur, heap.get(targetIdx));
                heap.set(targetIdx, parentVal);

                // 교환 이후 교환한 위치의 자식 노드들과 값 비교해야할 수 있으므로 cur을 교환 위치로 조정
                cur = targetIdx;
            }

        }

        // 가장 처음에 꺼낸 값 반환
        return target;
    }
}

public class Heap_Practice3 {
    // heap과 삭제 횟수 넣고 절대값이 큰 노드부터 출력하는 함수
    public static void solution(int[] nums, int deleteCnt) {
        // Num을 사용하는 MaxHeap2 생성
        MaxHeap2 maxHeap2 = new MaxHeap2();

        // IntStream 이용하여 MaxHeap2에 데이터 추가
        IntStream.of(nums).forEach(x -> maxHeap2.insert(x));

        // 삭제 횟수 계산을 위한 변수
        int cnt = 0;
        // MaxHeap 크기가 1이 되기 전 까지 loop 수행하여 데이터 하나씩 꺼내 출력. 단, cnt가 deleteCnt에 도달할 때 까지 출력하지 않음
        while (maxHeap2.heap.size() != 1) {
            // 최대 힙 구성이므로 최댓값 부터 출력됨
            Num cur = maxHeap2.delete();

            // 가장 큰 것 부터 deleteCnt 만큼은 출력 대상이 아니므로,
            // 값을 꺼냈지만 cnt가 deleteCnt에 도달할 때 까지 출력하지 않기 위해 지우고 건너뜀
            if(cnt++ < deleteCnt) {
                continue;
            }

            // 꺼낸 값(절대값)이 음수인지 양수인지 구분되지 않으므로, 원래 값을 복원하여 출력
            int oriVal = cur.isMinus ? cur.val * -1 : cur.val;
            System.out.print(oriVal + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int nums[] = {3, 0, -2, -5, 9, 6, -11, 20, -30};
        int deleteCnt = 1;
        solution(nums, deleteCnt); // 절댓값 기준으로 가장 큰 -30은 삭제되고 나머지가 출력
    }
}
