package CH03.Array.Practices;

// Practice7
// 2차원 배열 arr을 시계 방향 90도로 회전시킨 결과를 출력

// 입출력 예시
// arr :
// 1 2 3 4 5
// 6 7 8 9 10
// 11 12 13 14 15

// 결과 :
// 11 6 1
// 12 7 2
// 13 8 3
// 14 9 4
// 15 10 5

public class Arr_Practice7 {

    // 배열 출력할 함수
    static void printArr(int[][] arr) {
        for (int[] items : arr) {
            for (int item : items) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};

        // 90도 회전할 배열 생성
        // arr[행 숫자].length = 해당 이차원 배열의 열 숫자
        int[][] arr90 = new int[arr[0].length][arr.length]; // 이렇게 하면 해당 예제에서는 [5][3]으로 arr90이 생성

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                // rotate 변수로 arr 배열의 행을 arr90 배열의 열로 회전
                int rotate = arr.length - 1 - i; // ex) 이 경우 i가 0일때 rotate는 3 - 1 - 0 으로 2
                arr90[j][rotate] = arr[i][j]; // ex) 따라서 arr90[0][2]에 arr[0][0] 값이 들어감(arr 0행0열의 1이 arr90 0행 2열로 이동)
            }
        }

        // 회전 전/후 배열 출력
        System.out.println("** 원래 배열 **");
        printArr(arr);
        System.out.println("** 회전한 배열 **");
        printArr(arr90);
    }
}
