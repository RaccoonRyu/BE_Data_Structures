package CH04.HashTable.Practice;

// Practice2
// 정수형 배열 nums와 target이 주어졌을 때
// nums에서 임의의 두 수를 더해 target을 구할 수 있는지 확인하는 프로그램을 작성
// 두 수의 합으로 target을 구할 수 있으면 해당 값의 index를 반환하고, 없는 경우 null 반환

// 입출력 예시
// 입력
// nums : 7, 11, 5, 3
// target : 10
// 출력 : 0, 3

// nums : 8, 3, -2
// target : 6
// 출력 : 0, 2

// idea : for문을 두번 수행해서 바깥 for문에서 하나 잡고 안쪽 for문에서 밖에서 하나 잡은 것을 더해가며 구하는 방법 1
// 아니면 해시 테이블 사용해서 간단하게 구하기 -> 구하려는 수에서 배열 요소의 숫자를 뺀 것을 key, 해당 배열의 요소의 인덱스를 value로 구성해가며 탐색

import java.util.Arrays;
import java.util.Hashtable;

public class HT_Practice2 {

    public static int[] solution(int[] numbers, int target) {
        // 결과로 반환할 인덱스의 배열 생성. 인덱스에 해당하는 두 수를 더하는 것이므로 길이는 2 고정
        int[] result = new int[2];

        // 해시 테이블 생성 - Key의 데이터는 target - numbers 배열의 원소, Value의 데이터는 해당 원소의 인덱스
        Hashtable<Integer, Integer> ht = new Hashtable<>();

        // numbers 배열을 순회하며 해시 테이블에 데이터를 넣고 탐색
        for (int i = 0; i < numbers.length; i++) {
            // 현재 수와 더해서 target을 만들 수 있는 수가 해시 테이블에 있는지 확인
            // 이 수가 있다면, 현재 수(numbers[i])와 이 수의 인덱스 값을 합쳐서 target을 만들 수 있음
            if(ht.containsKey(numbers[i])) {
                result[0] = ht.get(numbers[i]); // 이전에 저장된 인덱스 가져오기
                result[1] = i; // // 현재 인덱스를 넣어줌

                // 더하려는 두 수의 인덱스의 배열을 return;
                return result;
            }

            // 해시 테이블에 target - numbers[i]를 Key로 저장하고, 현재 인덱스를 값으로 넣음
            // 이로써 이후 반복에서 이 값이 나타나면 target을 만들 수 있는 수를 찾았음을 알 수 있음
            ht.put(target - numbers[i], i);
        }

        // for문 다 돌 동안에도 데이터가 없다면 null 반환
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {7, 11, 5, 3};
        System.out.println(Arrays.toString(solution(nums, 10)));

        nums = new int[]{8, 3, -2};
        System.out.println(Arrays.toString(solution(nums, 6)));

        nums = new int[]{1, 2, 3};
        System.out.println(Arrays.toString(solution(nums, 12))); // 위의 수들을 갖고 target을 만들 수 없으므로 null 반환
    }
}
