package CH04.HashTable.Practice;

// Practice1
// 해시 테이블을 이용한 수 찾기
// 주어진 첫 번째 배열을 이용하여 해시 테이블을 초기화 한 후
// 두 번째 배열이 주어졌을 때 해당 배열 내 데이터가 해시 테이블에 있는지 확인하는 코드 작성

// 입출력 예시
// 입력
// 배열 1 : 1, 3, 5, 7, 9
// 배열 2 : 1, 2, 3, 4, 5
// 출력 : True, False, True, False, True

// idea : 간단한 해시테이블 이용 문제

import java.util.Hashtable;

public class HT_Practice1 {

    public static void solution(int[] arr1, int[] arr2) {
        // 해시 테이블 생성
        Hashtable<Integer, Integer> ht = new Hashtable<>();

        // 첫 번째 배열 사용하여 HashTable 초기화
        for (int i = 0; i < arr1.length; i++) {
            ht.put(arr1[i], arr1[i]);
        }

        // arr1을 가지고 만든 해시 테이블의 값이 arr2 배열 요소의 값과 동일한지 검사
        for (int i = 0; i < arr2.length; i++) {
            // 해시 테이블 내 arr2[i]의 요소 값에 해당하는 Key를 가졌는지 검사
            if(ht.containsKey(arr2[i])) {
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {1, 2, 3, 4, 5};
        solution(arr1, arr2);
    }
}
