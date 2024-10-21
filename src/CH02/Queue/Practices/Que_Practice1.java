package CH02.Queue.Practices;

// Practice1
// 카드 섞기
// 1부터 N 까지의 번호로 구성된 N장의 카드가 있고
// 1번 카드가 가장 위에 그리고 N번 카드는 가장 아래의 상태로 카드가 순서대로 쌓여있다.
// 아래 동작을 카드 한장만 남을 때 까지 반복했을 때 가장 마지막에 남는 카드 번호를 출력하시오.

// 1. 가장 위의 카드는 버림
// 2. 그 다음 위의 카드는 쌓여있는 카드의 가장 아래에 넣음

// 예시 입력
// N = 4
// 결과 = 6

// N = 7
// 결과 : 6

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class Que_Practice1 {
    // 마지막 카드를 찾는 함수
    public static int findLastCard(int N) {
        // N장의 카드를 쌓기 위한 큐 생성
        // 1부터 N까지의 숫자가 큐에 순서대로 들어감
        Queue queue = new LinkedList();
        /*for (int i = 1; i <= N; i++) {
            queue.add(i);
        } // 방법 1*/
        IntStream.range(1, N+1).forEach(x -> queue.add(x));

        // 위의 조건대로 구현하기 위한 반복 시작
        //int finalCard = 0;
        /*for (int i = 1; i <= N; i++) {
            if(queue.size() > 1) {
                queue.poll();
                queue.add(queue.poll());
            } else {
                finalCard = (int) queue.poll();
            }
        }// 방법 1 */

        while (queue.size() > 1) {
            // 첫 장은 버림
            queue.poll();
            // 그 다음 장은 꺼낸 후 큐에 다시 넣음(큐는 선입 선출이므로 가장 뒤에 들어감)
            int data = (int) queue.remove();
            queue.add(data);
        }

        // 마지막 남은 카드 한장을 꺼내서 반환
        //return finalCard;
        return (int) queue.remove();
    }

    public static void main(String[] args) {

        System.out.println(findLastCard(4)); // 4
        System.out.println(findLastCard(7)); // 6
        System.out.println(findLastCard(9)); // 2

    }
}
