package CH04.HashTable;

// Practice3
// 해시 충돌 해결 - 개방 주소법(제곱 탐사법)

// Practice1의 MyHashTable 상속
class MyHashTable3 extends MyHashTable {

    MyHashTable3(int size) {
        super(size);
    }

    // MyHashTable의 setValue 오버라이딩
    // 충돌 시 제곱 탐사법으로 충돌 해결
    public void setValue(int key, int data) {
        int idx = this.getHash(key);

        if(this.elemCnt == this.table.length) {
            System.out.println("Hash Table is Full!");
            return;
        } else if (this.table[idx] == null) {
            this.table[idx] = data;
        } else { // 해시 충돌 발생 지점. HashTable에 데이터는 꽉 차지 않았지만 idx에 해당하는 자리에 데이터가 있는 경우
            // 2의 n제곱만큼 증가시키면서 탐색할 인덱스를 계산
            int newIdx = idx;
            int cnt = 0;
            while (true) {
                // Math.pow 함수를 통해 2의 cnt 제곱수(cnt승) 구하여 newIdx에 더한 다음 table의 길이와 나머지 연산한다.
                // 따라서 해당 부분은 기존 인덱스에서 2의 cnt승 만큼 이동하며 탐색하기 위한 부분이다.
                newIdx = (newIdx + (int)Math.pow(2, cnt)) % this.table.length;
                if(this.table[newIdx] == null) {
                    break;
                }
                // 충돌 시 2의 n제곱을 증가시켜야 하므로 cnt 값을 1씩 증가
                cnt++;
            }
            this.table[newIdx] = data;
        }
        this.elemCnt++;
    }
}

public class HashTable_Practice3 {
    public static void main(String[] args) {
        MyHashTable3 myHashTable3 = new MyHashTable3(11); // 사이즈 11개
        myHashTable3.setValue(1, 10);
        myHashTable3.setValue(2, 20);
        myHashTable3.setValue(4, 40);
        myHashTable3.printHashTable(); // 10, 20, 40 들어감

        myHashTable3.setValue(1, 100); // 충돌 발생 지점
        myHashTable3.printHashTable(); // 2의 0승에서도 충돌, 2의 1승에서도 충돌하므로 2의 2승만큼 이동하여 데이터 100 추가(key 1->2->4->8)

        myHashTable3.setValue(1, 200); // 충돌 발생 : key 1->2->4->8->순회 후 key 5에 200 데이터 추가
        myHashTable3.setValue(1, 300); // 충돌 발생 : key 1->2->4->8->5->순회 후 key 10에 300 데이터 추가
        myHashTable3.setValue(1, 400); // 충돌 발생 : key 1->2->4->8->5->10->순회 후 key 9에 400 데이터 추가
        myHashTable3.printHashTable();
    }
}
