package CH03.Array;

// 기본 배열 자료형을 통해 자료구조로서의 배열로 기능하도록 작성
// 배열의 생성, 삽입, 삭제 기능 구현

import java.util.Arrays;

class MyArray {
    int[] arr;

    // 배열 초기 사이즈 설정 (생성자)
    MyArray(int size) {
        this.arr = new int[size];
    }

    // 배열에 데이터 삽입 (특정 위치에 특정 값)
    public void insertData(int index, int data) {
        if(index < 0 || index > this.arr.length) {
            System.out.println("Index Error!");
            return;
        }
        // 기존 배열 데이터 복사
        int[] arrDup = this.arr.clone();
        // 데이터 추가 시 배열 사이즈 또한 증가해야 하므로, 기존 배열에 기존 배열보다 사이즈가 하나 큰 배열 만들어서 할당
        this.arr = new int[this.arr.length + 1];

        // 데이터를 추가하려는 index 전 까지는 복사한 기존 배열 데이터를 순회하며 새로 만들어진 배열에 할당
        for (int i = 0; i < index; i++) {
            this.arr[i] = arrDup[i];
        }

        // 데이터를 추가하려는 index 다음부터 배열 끝자리 까지도 복사한 기존 배열 데이터를 순회하며 새로 만들어진 배열에 할당
        for (int i = index + 1; i < this.arr.length; i++) {
            this.arr[i] = arrDup[i-1];
        }

        this.arr[index] = data;
    }


    // 배열에서 특정 데이터 삭제
    public void removeData(int data) {
        int targetIndex = -1;

        // 배열에 삭제하려는 데이터가 있는지 확인
        for (int i = 0; i < this.arr.length; i++) {
            if(this.arr[i] == data) {
                targetIndex = i; // 데이터가 있다면 타겟인덱스를 해당 데이터가 있는 인덱스로 변경
                break;
            }
        }

        if(targetIndex == -1) {
            System.out.println("해당 데이터가 없습니다.");
        } else {
            // 기존 배열 데이터 복사
            int[] arrDup = this.arr.clone();
            // 데이터 삭제 시 배열 사이즈 또한 감소해야 하므로, 기존 배열에 기존 배열보다 사이즈가 하나 적은 배열 만들어서 할당
            this.arr = new int[this.arr.length - 1];

            // 지우려는 데이터의 index 전까지 새 배열에 기존 배열 데이터 할당
            for (int i = 0; i < targetIndex; i++) {
                this.arr[i] = arrDup[i];
            }

            // 지우려는 데이터의 index부터(해당 데이터는 삭제됐으므로) 배열 끝자리 까지도 복사한 기존 배열 데이터를 순회하며 새로 만들어진 배열에 할당
            for (int i = targetIndex; i < this.arr.length; i++) {
                this.arr[i] = arrDup[i+1];
            }
        }
    }
}

public class Array_Practice1 {
    public static void main(String[] args) {
        int size = 5;
        MyArray myArray = new MyArray(size);

        for (int i = 0; i < size; i++) {
            myArray.arr[i] = i + 1;
        }

        System.out.println(Arrays.toString(myArray.arr)); // [1, 2, 3, 4, 5] 출력

        myArray.arr[0] = 10; // 배열 내 index 0의 데이터 변경
        System.out.println(Arrays.toString(myArray.arr)); // [10, 2, 3, 4, 5] 출력

        myArray.insertData(2, 20); // index 2에 20 추가
        System.out.println(Arrays.toString(myArray.arr)); // [10, 2, 20, 3, 4, 5] 출력

        myArray.insertData(6, 60); // index 6에 60 추가
        System.out.println(Arrays.toString(myArray.arr)); // [10, 2, 20, 3, 4, 5, 60] 출력

        myArray.insertData(-1, 0); // Index Error 출력

        myArray.removeData(4); // 데이터 4 삭제
        System.out.println(Arrays.toString(myArray.arr)); // [10, 2, 20, 3, 5, 60] 출력

        myArray.removeData(5); // 데이터 5 삭제
        System.out.println(Arrays.toString(myArray.arr)); // [10, 2, 20, 3, 60] 출력

        myArray.removeData(99); // 해당 데이터 없으므로 해당 데이터가 없습니다. 출력
    }
}
