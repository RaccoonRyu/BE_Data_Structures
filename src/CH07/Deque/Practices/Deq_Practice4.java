package CH07.Deque.Practices;

// Practice4
// 데크 리사이즈
// 기본 데크 구조에서 데크 공간이 full 일 때 데이터를 추가하는 경우
// 데크 공간을 2배 씩 늘려주는 코드를 작성

class MyDeque2 {
    // deque 관리를 위한 배열과 front/rear 변수 생성
    int[] arr;
    int front = 0;
    int rear = 0;

    MyDeque2(int size)  {
        // 데크는 저번 Queue처럼 원형으로 작성하기 때문에 front가 가리키는 자리를 비워두기 위해 size + 1
        this.arr = new int[size + 1];
    }

    public boolean isEmpty() {
        // rear와 front가 같은 위치를 가리키면 empty
        if(this.front == this.rear) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        return (this.rear + 1) % this.arr.length == this.front;
    }

    // 데크 공간 늘려주는 코드 작성
    public void increaseSize() {
        // 기존 데크 데이터를 옮기기 위한 clone
        int[] arrTmp = this.arr.clone();
        // 데크의 사이즈 2배로 늘림
        this.arr = new int[this.arr.length * 2];

        // 기존 데크의 데이터를 사이즈 늘린 데크에 복사할 때 들고와주기 위해 사용하는 지점인 start, end 지점 설정
        // 복사 전 기존 데크에서의 스타트와 엔드 지점을 각각 front/rear에서 1씩 증가시켜서 저장
        int start = (this.front + 1) % arrTmp.length;
        int end = (this.rear + 1) % arrTmp.length;

        // 사이즈를 늘린 데크에 데이터 넣는 코드
        int idx = 1;
        for (int i = start; i != end; i = (i + 1) % arrTmp.length) {
            // 사이즈 증가시킨 데크에 클론시켜뒀던 기존 데크의 데이터 입력
            this.arr[idx++] = arrTmp[i];
        }

        // front, rear 재조정
        this.front = 0;
        this.rear = idx - 1;
    }

    public void addFirst(int data) {
        // 데크가 가득 찼을 때 메시지 대신 사이즈 늘리도록 변경
        if(this.isFull()) {
            increaseSize();
        }
        // front에 자료를 넣으면 다음에 front 방향으로 정보를 넣을 때 방향을 유지하기 위해 데크를 회전시켜야 함
        // ex) 0번 인덱스에 자료를 넣은 후 front 방향으로 정보를 넣으면 방향을 유지하기 위해 회전하여 4번 인덱스에 자료가 들어가야 함
        // 따라서 front에서 1을 빼고 회전을 위해 배열의 길이를 더한 다음 배열의 길이로 나머지 연산하면 회전하여 front 방향 다음 인덱스에 접근할 수 있다.
        // ex) 인덱스 0에 자료 넣음 => 0 - 1 + 5 % 5 = 4번 인덱스
        this.arr[front] = data;
        this.front = (this.front - 1 + this.arr.length) % this.arr.length;
    }

    public void addLast(int data) {
        // 데크가 가득 찼을 때 메시지 대신 사이즈 늘리도록 변경
        if(this.isFull()) {
            increaseSize();
        }
        // 리어 조정 먼저
        this.rear = (this.rear + 1) % this.arr.length;
        this.arr[this.rear] = data;
    }

    // 중간 지점에 데이터를 삽입하는 함수
    public void addMiddle(int data) {
        if(this.isFull()) {
            System.out.println("Deque is Full!");
            return;
        }

        // 리어에서 프론트를 빼면 데크에 원소들이 몇개 들어가있는지 셈
        int elements = this.rear - this.front;
        if(elements < 0) { // elements가 음수가 되는 경우 elements 구하기 (데크가 회전하기 때문에 front가 rear보다 큰 경우가 있음!)
            elements = this.arr.length + elements;
        }

        // 데크의 중간 지접을 찾기 위한 코드
        // 중간 지점 : 리어에서 원소/2를 뺀 후 원형으로 돌아가는 경우를 위해 배열의 길이를 더하고, 배열의 길이로 나머지연산 후 + 1
        int mid = (this.rear - elements / 2 + this.arr.length) % this.arr.length + 1;

        // 데크 내에서 데이터를 밀고 중간지점에 데이터 넣는 코드 작성
        // 데이터를 한칸씩 미는 코드
        int start = (this.rear + 1) % this.arr.length;
        int end = (this.rear - elements / 2 + this.arr.length) % this.arr.length;
        for (int i = start; i != end; i = (i - 1 + this.arr.length) % this.arr.length) {
            this.arr[i] = this.arr[(i - 1 + this.arr.length) % this.arr.length];
        }
        // 중간지점에 데이터를 넣는 코드
        this.arr[mid] = data;
        // 데이터를 넣었으니 리어를 한칸 이동시켜주는 코드
        this.rear = (this.rear + 1) % this.arr.length;

    }

    public Integer removeFirst() {
        if(isEmpty()) {
            System.out.println("Deque is Empty!");
            return null;
        }
        // 기본적으로 front가 가리키고있는 공간에는 데이터가 없으므로 그 다음 부분의 데이터를 remove
        this.front = (this.front + 1) % this.arr.length;
        return this.arr[this.front];
    }

    public Integer removeLast() {
        if(isEmpty()) {
            System.out.println("Deque is Empty!");
            return null;
        }

        int data = this.arr[this.rear];
        // 리어 조정 - 조정할 때 deque 안쪽으로 움직이기 때문에 -1하고 배열의 길이 더한 후 배열의 길이로 나머지 연산
        this.rear = (this.rear -1 + this.arr.length) % this.arr.length;
        return data;
    }

    public void printDeque() {
        int start = (this.front + 1) % this.arr.length;
        int end = (this.rear + 1) % this.arr.length;

        for (int i = start; i != end; i = (i + 1) %this.arr.length) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }
}

public class Deq_Practice4 {
    public static void main(String[] args) {
        MyDeque2 myDeque1 = new MyDeque2(5); // 길이 5(+1하므로 6)
        myDeque1.addLast(1);
        myDeque1.addLast(2);
        myDeque1.addLast(3);
        myDeque1.addLast(4);
        myDeque1.addLast(5);
        myDeque1.printDeque(); // 1 2 3 4 5 추가. 데크는 꽉 찬 상황

        myDeque1.addLast(6);
        myDeque1.addLast(7);
        myDeque1.addLast(8);
        myDeque1.addLast(9);
        myDeque1.addLast(10);
        myDeque1.printDeque(); // 데크가 늘어나 다음 데이터가 잘 추가된 것을 확인 가능

        myDeque1.removeLast();
        myDeque1.removeLast();
        myDeque1.addFirst(100);
        myDeque1.addFirst(200);
        myDeque1.printDeque(); // 여기까지 데크 사이즈는 12

        myDeque1.addFirst(300);
        myDeque1.addFirst(400);
        myDeque1.addFirst(500);
        myDeque1.printDeque(); // 데크가 한번 더 늘어나 12개를 초과한 후에도 데이터가 잘 들어감이 확인

    }
}
