package CH03.Array.Practices;

// Practice3
// 배열 arr의 데이터 순서를 거꾸로 변경
// 추가 배열을 사용하지 않고 구현

// 입출력 예시
// arr : 1, 3, 5, 7, 9
// 결과 : 9, 7, 5, 3, 1

import java.util.Arrays;

public class Arr_Practice3 {
    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 9};

        // 중간 지점(여기서는 5)에서 각 숫자끼리만 변경(1->9, 3->7)해주면 되므로 length 나누기 2
        // 만약 나누기 2 하지 않고 끝까지 loop 수행하면 배열 요소들이 원위치한다.
        for (int i = 0; i < arr.length / 2; i++) {
            // 자리 바꾸기를 위한 임시 변수
            int tmp = arr[i];
            // 현재 arr의 위치 기준으로 변경하려는 위치의 값 대입
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }

        // 배열의 간편한 출력을 위한 메서드 Array.toString(배열) 사용
        System.out.println(Arrays.toString(arr));
    }
}
