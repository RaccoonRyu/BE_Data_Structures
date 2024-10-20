package CH04.HashTable;

// Practice5
// 해시 충돌 해결 - 분리 연결법
// 분리 연결법은 연결리스트를 이용하는 방법이다.

class Node {
    // key, value를 사용하기 위한 변수 key 추가
    int key;
    int data;
    Node next;

    Node() {}
    Node(int key, int data, Node next) {
        this.key = key;
        this.data = data;
        this.next = next;
    }
}

//
class LinkedList {
    Node head;

    LinkedList() {}
    LinkedList(Node node) {
        this.head = node;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void addData(int key, int data) {
        // key, data로 노드 생성
        // head가 없으면 노드를 생성하여 헤드로 만들어줌
        if(this.head == null) {
            this.head = new Node(key, data, null);
        } else {
            // 현재 노드를 헤드로부터 while문 반복하여 해당 노드의 가장 끝까지 이동 후 노드 끝에 노드를 만들어 붙여줌
            Node cur = this.head;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = new Node(key, data, null);
        }
    }

    public void removeData(int key) {
        if(this.isEmpty()) {
            System.out.println("List is Empty");
            return;
        }
        // key값 기준으로 데이터 삭제
        Node cur = this.head;
        Node pre = cur;
        while (cur != null) {
            if(cur.key == key) {
                if(cur == this.head) {
                    this.head = cur.next;
                } else {
                    pre.next = cur.next;
                }
                break;
            }
            pre = cur;
            cur = cur.next;
        }
    }

    // key값 기준으로 데이터 찾기
    public Integer findData(int key) {
        if (this.isEmpty()) {
            System.out.println("List is empty");
            return null;
        }
        Node cur = this.head;
        while (cur != null) {
            if(cur.key == key) {
                System.out.println("Data Exist!");
                return cur.data;
            }
            cur = cur.next;
        }
        System.out.println("Data not found!");
        return null;
    }

    public void showData() {
        if(this.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}

// 분리 연결법으로 해시 충돌 해결하는 해시 테이블
class MyHashTable5 {
    // 해시 테이블로 사용할 링크드 리스트의 배열 생성
    LinkedList[] table;

    MyHashTable5(int size) {
        this.table = new LinkedList[size];
        for (int i = 0; i < this.table.length; i++) {
            this.table[i] = new LinkedList(null);
        }
    }

    public int getHash(int key) {
        return key % this.table.length;
    }

    // 분리 연결법 : 충돌 시 충돌 여부에 상관없이 해당 위치 연결리스트에 addData로 연결만 시키면 됨
    public void setValue(int key, int data) {
        int idx = this.getHash(key);
        this.table[idx].addData(key, data);
    }

    public int getValue(int key) {
        int idx = this.getHash(key);
        int data = this.table[idx].findData(key);
        return data;
    }

    public void removeValue(int key) {
        int idx = this.getHash(key);
        this.table[idx].removeData(key);
    }

    public void printHashTable() {
        System.out.println("** Hash Table **");
        for (int i = 0; i < this.table.length; i++) {
            System.out.print(i + ": ");
            this.table[i].showData();
        }
    }
}

public class HashTable_Practice5 {
    public static void main(String[] args) {
        MyHashTable5 myHashTable5 = new MyHashTable5(11); // 연결리스트 배열 사이즈 11개
        myHashTable5.setValue(1, 10); //
        myHashTable5.setValue(2, 20);
        myHashTable5.setValue(3, 30);
        myHashTable5.printHashTable();
        myHashTable5.setValue(12, 11);
        myHashTable5.setValue(23, 12);
        myHashTable5.setValue(34, 13); // 해시 테이블 사이즈가 11이므로 나머지가 1이라 key 1에 연속하여 데이터 추가

        myHashTable5.setValue(13, 21);
        myHashTable5.setValue(24, 22);
        myHashTable5.setValue(35, 23); // 해시 테이블 사이즈가 11이므로 나머지가 2라서 key 2에 연속하여 데이터 추가

        myHashTable5.setValue(5, 1);
        myHashTable5.setValue(16, 2);
        myHashTable5.setValue(27, 3); // 해시테이블 사이즈가 11이므로 나머지가 5라서 key 5에 연속하여 데이터 추가
        myHashTable5.printHashTable();

        System.out.println("** Key 값으로 해당 데이터 가져오기");
        System.out.println(myHashTable5.getValue(1));
        System.out.println(myHashTable5.getValue(12));

        System.out.println("** Key 값으로 해당데이터 삭제");
        myHashTable5.removeValue(1);
        myHashTable5.removeValue(5);
        myHashTable5.removeValue(16);
        myHashTable5.printHashTable();
    }
}
