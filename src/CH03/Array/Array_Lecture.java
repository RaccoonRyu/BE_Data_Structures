package CH03.Array;

import java.util.ArrayList;
import java.util.Arrays;

public class Array_Lecture {
    public static void main(String[] args) {
        // 배열 예제 코드
        // 1차원 배열
        System.out.println("** 1차원 배열 **");
        int[] arr = {1, 2, 3, 4, 5};
        for (int item : arr) { // 좌측 : 배열의 요소에 해당하는 자료형 변수 / 우측 : 반복문에서 순회할 배열
            System.out.println("item = " + item);
        }

        arr[1] = 100 ; // 배열 내 특정 위치의 데이터 수정
        System.out.println("arr = " + Arrays.toString(arr));

        // 2차원 배열
        System.out.println("** 2차원 배열 **");
        int[][] arr2 = {{1, 2, 3},{4, 5, 6}};
        System.out.println(arr2[0][1]); // 1행 2열 데이터 출력

        for (int[] row : arr2) { // 2차원 배열 안의 1차원 배열(행)을 꺼냄
            for (int item : row) { // 꺼낸 1차원 배열 내 요소를 꺼내서 출력
                System.out.println("item = " + item);
            }
        }

        // ArrayList - 1차원, 2차원
        System.out.println("** ArrayList **");
        ArrayList list1 = new ArrayList(Arrays.asList(1, 2, 3)); // ArrayList 생성 시 데이터를 바로 만들어서 생성
        System.out.println("list1 = " + list1);
        list1.add(4);
        list1.add(5);
        System.out.println("list1 = " + list1);
        list1.remove(2); // Index를 통한 삭제. 3 제거
        System.out.println("list1 = " + list1);
        list1.remove(Integer.valueOf(2)); // 값 자체를 통한 삭제
        System.out.println("list1 = " + list1); // 2 제거

        // 2차원 (ArrayList 안에 ArrayList)
        ArrayList list2d = new ArrayList(); // 2차원으로 사용할 바깥 ArrayList 생성
        ArrayList list1d1 = new ArrayList(Arrays.asList(1, 2, 3)); // 2차원 ArrayList 안에 들어갈(각 행) 1차원 ArrayList 생성
        ArrayList list1d2 = new ArrayList(Arrays.asList(4, 5, 6));
        list2d.add(list1d1);
        list2d.add(list1d2);
        System.out.println("list1d1 = " + list1d1);
        System.out.println("list1d2 = " + list1d2);
        System.out.println("list2d = " + list2d); // 2차원처럼 만들어진 ArrayList 출력 [[1, 2, 3], [4, 5, 6]]
    }
}
