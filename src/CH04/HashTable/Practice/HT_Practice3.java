package CH04.HashTable.Practice;

// Practice3
// 참고 - HashTable vs HashMap 차이점

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HT_Practice3 {

    public static void main(String[] args) {
        // HashTable
        Hashtable<Integer, Integer> ht = new Hashtable<>();
        ht.put(0, 10);
        System.out.println(ht.get(0));

        // HashMap
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0, 10);
        System.out.println(hm.get(0));

        // 둘은 Map 인터페이스로부터 구현된 클래스이기 때문에 다형성 사용 가능
        Map<Integer, Integer> ht1 = ht;
        Map<Integer, Integer> hm1 = hm;
        System.out.println(ht1.get(0));
        System.out.println(hm1.get(0));

        // 차이점
        /*ht.put(null, -999);
        System.out.println(ht.get(null)); // HashTable 같은 경우에는 Key로 null을 사용하면 NPE 발생 (사용 불가)*/

        hm.put(null, -999);
        System.out.println(hm.get(null)); // HashMap은 Key로 null 사용 가능

        // HashTable과 HashMap 공통점과 차이점 정리
        // 공통점 : 둘 다 Map 인터페이스를 구현한 것 -> Key, Value 쌍으로 이루어진 사용성 측면에서 거의 동일
        // 차이점 :
        // Key에 Null 사용 여부(HT : 불가 / HM : 가능)
        // Thread-safe 측면에서 HT는 O (멀티 스레드 환경에서 우수), HM은 X (싱글 스레드 환경에서 우수 - 코테에서 주로 사용)
        // 참고) synchronizedMap, ConcurrentHashMap을 사용하면 HashMap을 Thread-safe하게 사용할 수 있다.

        // Thread-safe란?
    }
}
