package CH03.Array.Practices;

// Practice1
// 배열 arr의 모든 데이터에 대해 짝수 데이터의 평균과 홀수 데이터의 평균 출력

// 입출력 예시
// 배열 arr : 1, 2, 3, 4, 5, 6, 7, 8, 9
// 결과
// 짝수 평균 : 5.0
// 홀수 평균 : 5.0

import java.util.Scanner;

public class Arr_Practice1 {
    public static void main(String[] args) {
        // 입출력 예시와 같이 배열
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // 평균 계산용 변수들 선언
        float evenSum = 0; // 짝수 합
        float oddSum = 0; // 홀수 합
        int evenCnt = 0; // 짝수 수
        int oddCnt = 0; // 홀수 수

        for (int item : arr) {
            if(item % 2 == 0) {
                evenSum += item;
                evenCnt++;
            } else {
                oddSum += item;
                oddCnt++;
            }
        }

        System.out.println("짝수 평균 : " + evenSum / evenCnt);
        System.out.println("홀수 평균 : " + oddSum / oddCnt);
    }
}
