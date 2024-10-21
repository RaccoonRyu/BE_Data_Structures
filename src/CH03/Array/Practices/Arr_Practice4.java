package CH03.Array.Practices;

// Practice4
// 배열 arr에서 peek 값 모두 출력
// peek 값 : 배열의 어떤 요소가 있을 때, 해당 요소의 값이 좌-우 요소의 값보다 크면 이를 peek값이라 함.

// 입출력 예시) arr : 3, 1, 2, 6, 2, 2, 5, 1, 9, 10, 1, 11
// 결과 : 3, 6, 5, 10, 11

public class Arr_Practice4 {
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 6, 2, 2, 5, 1, 9, 10, 1, 11};

        for (int i = 0; i < arr.length; i++) {
            // 배열의 가장 처음 값이 peek값인지 확인
            // 가장 처음 인덱스 값은 비교 가능한 값이 바로 오른쪽 값이므로 i+1 값과 비교
            if (i == 0 && arr[i] > arr[i+1]) {
                System.out.print(arr[i] + ", ");
            // 배열의 가장 마지막 값이 peek값인지 확인
            // 가장 끝 인덱스 값은 비교 가능한 값이 바로 왼쪽 값이므로 i-1 값과 비교
            } else if(i == arr.length - 1 && arr[i] > arr[i-1]) {
                System.out.print(arr[i]);
            // 배열의 중간 값이 peek 값인지 확인
            // 중간 값은 비교 가능한 값이 왼쪽, 오른쪽 값이므로 i-1 / i+1 값과 모두 비교
            } else {
                if(arr[i] > arr[i-1] && arr[i] > arr[i+1]) {
                    System.out.print(arr[i] + ", ");
                }
            }
        }
        System.out.println();
    }
}
