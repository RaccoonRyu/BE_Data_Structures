package CH07.Deque.Practices;

// Practice1
// 데이터 재 정렬
// D_0 -> D_1 -> ... -> D_n-1 -> D_n 순으로 되어있는 데이터를
// D_0 -> D_n -> D_1 -> D_n-1 -> ... 순이 되도록 재정렬 하시오.

// 입력 예시
// 입력 데이터 : 1 -> 2 -> 3 -> 4 -> 5
// 출력 데이터 : 1 -> 5 -> 2 -> 4 -> 3

// 자료 구조 문제풀이 팁 : 입력과 출력 데이터가 어떻게 변화되는지를 확인하고 어떤 자료구조를 사용할지 선택하기!

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.stream.IntStream;

public class Deq_Practice1 {

    public static void reorderData(int[] arr) {
        // 파라미터로 들어오는 arr를 재 정렬 하기 위한 데크 생성
        Deque deque = new ArrayDeque();

        // 재 정렬 결과를 넣을 ArrayList 생성
        ArrayList resultList = new ArrayList();

        // 배열 데이터를 데크로 넣음
        // IntStream.of(배열) => 배열을 IntStream으로 만들어줌
        // 이후 forEach를 통해 데크에 Last 방향으로 데이터 입력
        IntStream.of(arr).forEach(x->deque.addLast(x));
        System.out.println(deque);

        // 데크가 빌 때 까지 데크에 넣은 배열 데이터를 정렬
        while (!deque.isEmpty()) {
            // 위에서 생성한 재 정렬 결과를 넣을 ArrayList에 데크의 첫 방향 데이터 빼서 add
            resultList.add(deque.removeFirst());

            // 이후 데크의 비어있음 여부를 확인하고 데크의 마지막 방향 데이터 빼서 add
            if(!deque.isEmpty()) {
                resultList.add(deque.removeLast());
            }
        } // 해당 loop를 반복하면 첫 데이터 뺌 - 마지막 데이터를 뺌이 반복되며 D_0 -> D_n -> D_1 -> D_n-1 -> ... 으로 정렬함

        // 재 정렬된 데이터 출력을 위해 출력함수 호출
        System.out.println("** 정렬 전 **");
        printData(arr); // 정렬 전은 파라미터로 받은 arr 그대로 넣으면 됨
        System.out.println("** 정렬 후 **");
        // ArrayList를 Stream으로 바꾸고, Stream의 mapToInt와 그 속에서 형변환, toArray()를 사용하여 ArrayList를 arr로 변환하여 넣음
        // mapToInt() : 스트림의 요소 각각의 형을 int로 변경 후 IntStream 생성
        // toArray() : 앞에서 만들어진 Stream을 Array로 변경
        printData(resultList.stream().mapToInt(x -> (int)x).toArray());
    }

    // 데이터 출력을 위한 함수
    static void printData(int[] arr) {
        // 배열의 마지막 원소 전까지 화살표 붙여서 출력
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + " -> " );
        }
        // 마지막 원소 뒤에는 화살표가 필요 없으므로 그냥 출력
        System.out.println(arr[arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        reorderData(arr1);

        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        reorderData(arr2);
    }
}
