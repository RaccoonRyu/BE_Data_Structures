package CH03.Array.Practices;

// Practice2
// 배열 arr에서 target에 해당하는 값의 인덱스 출력
// 해당 값이 여러 개인 경우 가장 큰 인덱스 출력

// 입출력 예시) 배열 arr : 1, 1, 100, 1, 1, 1, 100
// target : 100
// 결과 : 6

public class Arr_Practice2 {
    public static void main(String[] args) {
        // 입출력 예시와 동일하게 배열 생성
        int arr[] = {1, 1, 100, 1, 1, 1, 100};

        // 찾고자 하는 target 예시처럼 선언
        int target = 100;

        // target값을 갖는 최대 인덱스 표현을 위한 변수 선언
        int maxIdx = -1;

        for (int i = 0; i < arr.length; i++) {
            // 배열에 target과 같은 값이 있는지 검사
            if(arr[i] == target) {
                // i가 기존의 maxIdx보다 더 크면 maxIdx를 i로 갱신 (최대 인덱스를 출력해야 하므로)
                if(i > maxIdx) {
                    maxIdx = i;
                }
            }
        }

        // target이 배열에 존재하면 maxIdx가 변경되므로 변경된 maxIdx 출력
        if(maxIdx >= 0) {
            System.out.println("결과 : " + maxIdx);
        }
    }
}
