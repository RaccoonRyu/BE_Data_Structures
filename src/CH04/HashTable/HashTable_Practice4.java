package CH04.HashTable;

// Practice4
// 해시 충돌 해결 - 개방 주소법(이중 해싱)

// Practice1의 MyHashTable 상속
class MyHashTable4 extends MyHashTable {
    int c;

    MyHashTable4(int size) {
        super(size);
        // 소수같은 경우에는 Hash Table의 사이즈가 정해졌을 때 미리 구해둘 수 있음
        this.c = this.getHashC(size);
    }

    // 해시테이블의 사이즈보다 작은 소수(1과 자기 자신으로만 나눠지는 수) 구하는 함수
    public int getHashC(int size) {
        int c = 0;

        if(size <= 2) {
            return size;
        }

        // 해시 테이블의 사이즈보다 '약간' 작은 소수를 구하기 위한 반복문
        for (int i = size - 1; i > 2; i--) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) { // i가 j 범위 안의 값으로 나눠떨어지면 소수가 아님
                    isPrime = false;
                    break;
                }
            }
            // 위의 소수 검증로직을 통과하면 해당 소수 값을 c에 대입
            if (isPrime) {
                c = i;
                break;
            }
        }
        return c;
    }

    // 두번째 해시 함수
    public int getHash2(int key) {
        int hash = 1 + key % this.c; // 이때 c는 해시테이블의 사이즈보다 작은 소수
        return hash;
    }

    // MyHashTable의 setValue 오버라이딩
    // 충돌 시 이중 해싱으로 충돌 해결
    public void setValue(int key, int data) {
        int idx = this.getHash(key);

        if(this.elemCnt == this.table.length) {
            System.out.println("Hash Table is full!");
            return;
        } else if (this.table[idx] == null) {
            this.table[idx] = data;
        } else { // 충돌 지점
            // 이중 해싱은 충돌 시 이동할 값을 얻기 위해 두번째 해시함수를 사용함
            int newIdx = idx;
            int cnt = 1;
            while (true) {
                // 충돌이 발생하면 cnt만큼 곱하며 newIdx를 가중한 다음 테이블의 길이로 나머지 연산
                newIdx = (newIdx + this.getHash2(newIdx) * cnt) % this.table.length;
                if (this.table[newIdx] == null) {
                    break;
                }
                cnt++;
            }
            this.table[newIdx] = data;
        }
        this.elemCnt++;
    }
}

public class HashTable_Practice4 {
    public static void main(String[] args) {
        MyHashTable4 myHashTable4 = new MyHashTable4(11); // 사이즈 11개
        myHashTable4.setValue(1, 10);
        myHashTable4.setValue(2, 20);
        myHashTable4.setValue(3, 30);
        myHashTable4.printHashTable(); // 10, 20, 30


        myHashTable4.setValue(1, 100); // 충돌 발생 : key 1에서 충돌-> 1을 통해 이중해싱으로 새 키 3 가져옴 -> key 3에서 충돌 -> 다시 이중 해싱해서 새 키 0 가져옴 -> 충돌 없이 key 0에 100 데이터 대입
        myHashTable4.setValue(1, 200);
        myHashTable4.setValue(1, 300);
        myHashTable4.printHashTable();
    }
}
