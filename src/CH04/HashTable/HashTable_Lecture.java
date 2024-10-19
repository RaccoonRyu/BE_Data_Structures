package CH04.HashTable;

import java.util.Hashtable;
import java.util.Map;

public class HashTable_Lecture {
    // 해시 함수
    public static int getHash(int key) {
        return key % 5; // 이 때 뒤의 숫자는 해시 테이블의 크기
    }

    public static void main(String[] args) {
        // 기본 해시 테이블 사용 방법
        Hashtable<String, Integer> hashTable= new Hashtable();

        // 데이터 추가
        hashTable.put("key1", 10);
        hashTable.put("key2", 20);
        hashTable.put("key3", 30);

        for (Map.Entry<String, Integer> item : hashTable.entrySet()) { // entrySet() : Hash Table 안에 K, V로 대응된 데이터 전체
            System.out.println(item.getKey() + " - " + item.getValue());
        }

        // 해시 충돌 케이스 (해시 함수 사용)
        Hashtable<Integer, Integer> hashTable2 = new Hashtable();

        // 데이터 추가 시 key 부분에 key값을 바로 쓰지 않고 위에서 만든 해시 값으로 데이터 추가
        hashTable2.put(getHash(1), 10);
        hashTable2.put(getHash(2), 20);
        hashTable2.put(getHash(3), 30);
        hashTable2.put(getHash(4), 40);
        hashTable2.put(getHash(5), 50);

        System.out.println("** 충돌 전 **");
        for (Map.Entry<Integer, Integer> item : hashTable2.entrySet()) { // entrySet() : Hash Table 안에 K, V로 대응된 데이터 전체
            System.out.println(item.getKey() + " - " + item.getValue());
        }

        // 충돌 테스트를 위한 해시 값으로 데이터 추가
        hashTable2.put(getHash(6), 60);
        System.out.println("** 충돌 후 **");
        for (Map.Entry<Integer, Integer> item : hashTable2.entrySet()) { // entrySet() : Hash Table 안에 K, V로 대응된 데이터 전체
            System.out.println(item.getKey() + " - " + item.getValue());
        } // getHash를 통해 얻은 key가 1일 때 기존의 값 10에서 60으로 변경 (충돌)

    }
}
