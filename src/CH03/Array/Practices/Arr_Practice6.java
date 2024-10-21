package CH03.Array.Practices;

// Practice6
// 배열 arr에서 중복 값을 제거한 새 배열을 만드시오.

// 입출력 예시
// arr : 1, 5, 3, 2, 2, 3, 1, 4, 1, 2, 3, 5
// 결과 : 1, 5, 3, 2, 4

// 사실 HashSet을 사용하면 상당히 간단하게 해결이 가능..

public class Arr_Practice6 {
    public static void main(String[] args) {
        int[] arr = {1, 5, 3 ,2, 2, 3, 1, 4, 1, 2, 3, 5};

        // 중복을 제거해 결과로 만들 배열을 생성한다.
        int[] resultArr = new int[arr.length];

        // 결과로 만들 배열의 인덱스를 관리하기위한 변수를 선언한다
        int cnt = 0;

        for (int i = 0; i < arr.length; i++) {
            // arr의 값이 중복된 데이터인지 아닌지를 체크하는 변수
            boolean dupYn = false;

            // 중복을 제거해 결과로 만들 배열을 반복하며 중복 체크
            // 중복되지 않은 데이터들을 쌓기 위해 cnt만큼만 반복문을 수행한다.
            for (int j = 0; j < cnt; j++) {
                // arr[i]의 값이 이미 resultArr[j]에 들어가있다면 중복 변수에 true
                if(arr[i] == resultArr[j]) {
                    dupYn = true;
                }
            }
            // 루프를 수행했음에도 중복이 없다는 의미(resultArr에 없는 데이터)로 arr[i]의 값을 resultArr에 추가
            // 추가 후 cnt를 증가시켜서 다음 중복 체크 시 사용한다.
            if(dupYn == false) {
                resultArr[cnt++] = arr[i];
            }
        }
        for (int i = 0; i < cnt; i++) {
            System.out.print(resultArr[i] + " ");
        }
        System.out.println();
    }
}
