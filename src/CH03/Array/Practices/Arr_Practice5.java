package CH03.Array.Practices;

// Practice5
// 배열 arr의 값을 오름차순으로 출력

// 입출력 예시
// arr : 5, 3, 1, 4, 6, 1
// 결과 : 1, 1, 3, 4, 5, 6

public class Arr_Practice5 {
    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 4, 6, 1};

        // 정렬한 데이터를 확인하기 위한 배열 (0이면 미정렬, 1이면 정렬)
        int[] ordered = new int[arr.length];

        // 값 정렬을 위한 변수
        int orderCnt = 0;
        int minVal = Integer.MAX_VALUE; // 가장 작은 값들로 배열을 정렬하기 때문에 초기 비교값은 정수의 최댓값
        int minIdx = -1;

        while (orderCnt < arr.length) {
            // loop 돌면서 arr 배열의 값이 minVal보다 작고 이미 정렬한 데이터가 아닌 경우
            for (int i = 0; i < arr.length; i++) {
                if(arr[i] < minVal && ordered[i] == 0) {
                    // minVal에 arr 배열의 값을 넣고
                    minVal = arr[i];
                    // 최소 인덱스 또한 arr배열의 인덱스를 넣어줌
                    minIdx = i;
                }
            }

            // for loop 이후 최소 인덱스가 -1이 아니면 해당 값을 출력하고 해당 값의 인덱스를 정렬했다는 의미로 정렬 확인 배열을 갱신
            // 그 다음 반복을 위해 orderCnt도 증가
            if(minIdx != -1) {
                System.out.print(minVal + " ");
                ordered[minIdx] = 1;
                orderCnt++;
            }

            // 그 다음 반복에서 다시 비교를 위해 minVal과 minIdx를 각각 정수 최댓값과 -1로 초기화
            minVal = Integer.MAX_VALUE;
            minIdx = -1;
        }
        System.out.println();
    }
}
