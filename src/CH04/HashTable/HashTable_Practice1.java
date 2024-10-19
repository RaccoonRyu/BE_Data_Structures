package CH04.HashTable;

// Practice1
// 해시 테이블 배열로 직접 구현

class MyHashTable {
    Integer[] table;
    int elemCnt;

    MyHashTable(){}
    MyHashTable(int size) {
        this.table = new Integer[size];
        this.elemCnt = 0;
    }

    public int getHash(int key) {
        return key % this.table.length; // key를 테이블의 길이로 나눈 나머지를 Hash값으로 사용
    }

    public void setValue(int key, int data) {
        int idx = this.getHash(key); // 데이터를 넣을 해시 값을 구함
        this.table[idx] = data;
        this.elemCnt++;
    }

    public int getValue(int key) {
        int idx = this.getHash(key); // 데이터를 가져올 해시 값을 구함
        return this.table[idx]; // 해시 값에 해당하는 데이터를 반환
    }

    public void removeVal(int key) {
        int idx = this.getHash(key); // 데이터를 삭제할 해시 값을 구함
        this.table[idx] = null; // 해시 값에 해당하는 데이터 삭제(null 대입)
        this.elemCnt--;
    }
    
    public void printHashTable() {
        System.out.println("** Hash Table **");
        for (int i = 0; i < this.table.length; i++) {
            System.out.println(i + ": " + this.table[i]);
        }
    }
}

public class HashTable_Practice1 {
    public static void main(String[] args) {
        MyHashTable myHashTable = new MyHashTable(7);
        myHashTable.setValue(1, 1);
        myHashTable.setValue(2, 2);
        myHashTable.setValue(3, 3);
        myHashTable.setValue(4, 4);
        myHashTable.setValue(5, 5);
        myHashTable.printHashTable();
        myHashTable.setValue(8, 6); // 해시 충돌로 인해 데이터가 1에서 6으로 변경
        myHashTable.printHashTable();
    }
}
