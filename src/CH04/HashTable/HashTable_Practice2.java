package CH04.HashTable;

// Practice2
// 해시 충돌 해결 - 개방 주소법(선형 탐사법)

// Practice1의 MyHashTable 상속
class MyHashTable2 extends MyHashTable {

    MyHashTable2(int size) {
        super(size);
    }

    // MyHashTable의 setValue 오버라이딩
    // 충돌 시 선형 탐사법으로 충돌 해결
    public void setValue(int key, int data) {
        int idx = this.getHash(key);

        if(this.elemCnt == this.table.length) { // HashTable에 데이터가 꽉 찬 경우
            System.out.println("Hash Table is Full!");
            return;
        } else if (this.table[idx] == null) { // HashTable에 데이터는 꽉 차지 않았지만 비어있는 경우
            this.table[idx] = data;
        } else { // 해시 충돌 발생 지점. HashTable에 데이터는 꽉 차지 않았지만 idx에 해당하는 자리에 데이터가 있는 경우
            int newIdx = idx; // 충돌 지점에서부터 순회를 위한 newIdx 선언
            while (true) {
                newIdx = (newIdx + 1) % this.table.length; // 충돌이 일어난 인덱스의 다음으로 순회
                if(this.table[newIdx] == null) { // 순회 중 HashTable에 빈 공간이 있다면 반복문 탈출
                    break;
                }
            }
            // 반복문 탈출한 newIdx 자리에 데이터 추가
            this.table[newIdx] = data;
        }
        elemCnt++;
    }
}

public class HashTable_Practice2 {
    public static void main(String[] args) {
        MyHashTable2 myHashTable2 = new MyHashTable2(5);
        myHashTable2.setValue(1, 1);
        myHashTable2.setValue(3, 3);
        myHashTable2.printHashTable(); // [1=1, 3=3] 출력

        myHashTable2.setValue(1, 10); // 충돌 발생
        myHashTable2.printHashTable(); // 충돌이 발생한 지점의 다음 공간이 비었으므로 데이터 2=10 추가. [1=1, 2=10, 3=3] 출력

        myHashTable2.setValue(1, 20);
        myHashTable2.setValue(1, 30); // 충돌 발생하여 다음 빈 공간에 데이터 4=20, 0=30 추가. [0=30, 1=1, 2=10, 3=3, 4=20] 출력
        myHashTable2.setValue(1, 40); // HashTable이 가득 찼으므로 Hash Table is Full! 출력
        myHashTable2.printHashTable();
    }
}
