package CH02.Queue.Practices;

// Practice2
// 요세푸스 문제
// N, K가 주어졌을 때 (N, K) 요세푸스 순열을 구하시오.
// N, K는 N >= K 를 만족하는 양의 정수이며
// 1부터 N번까지 N명이 순서대로 원을 이뤄 모여있을 때 원을 따라 순서대로 K번째 사람을 제외한다.
// 모든 사람이 제외될 때 까지 반복하며 제외되는 순서가 요세푸스 순열이다.
// ex) 1 2 3 4 5가 있을 때 2번째인 2를 제외하고, 그 다음 2번째인 4를 제외하고 ... 마지막에 3을 제외한 결과를 순열로 만든 것
// 큐의 요소를 한번씩 꺼냈다가 K번째가 될 때마다 순열에 넣어버리고, 그 외의 경우는 다시 큐에 넣어버림

// 예시 입력
// 입력 : N = 5, K = 2
// 결과 : 2, 4, 1, 5, 3

// 입력 : N = 7, K = 3
// 결과 : 2, 4, 1, 5, 3

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class Que_Practice2 {

    public static ArrayList getJosephusPermutation(int N, int K) {
        // 1부터 N만큼의 큐 생성
        Queue queue = new LinkedList();
        IntStream.range(1, N+1).forEach(x -> queue.add(x));

        // 요세푸스 순열 결과를 리턴할 ArrayList 생성
        ArrayList list = new ArrayList();

        // K번째 사람 뽑기
        // K번째가 됐다는 것을 계산하기 위한 cnt 변수 생성
        int cnt = 0;
        while (!queue.isEmpty()) {
            // 큐에서 데이터 하나 꺼내고 K번째 계산을 위해 cnt 증가
            int data = (int)queue.remove();
            cnt++;

            // cnt를 K로 나눈 나머지가 0일 때(큐에 들어간 요소가 K번째 일 때) 해당 요소를 제외하고 순열 결과를 위해 만든 ArrayList에 넣어준다.
            if(cnt % K == 0) {
                list.add(data);
            } else {
                // 그 외의 경우에는 다시 큐에 삽입
                queue.add(data);
            }
        }
        // 반복문 종료 이후에 결과 반환
        return list;
    }

    public static void main(String[] args) {
        System.out.println(getJosephusPermutation(5, 2)); // [2, 4, 1, 5, 3]
        System.out.println(getJosephusPermutation(7, 3)); // [3, 6, 2, 7, 5, 1, 4]
    }
}
