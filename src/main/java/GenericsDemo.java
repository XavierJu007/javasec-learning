import java.util.HashMap;
import java.util.Map;

public class GenericsDemo {
    public static void main(String[] args) {
        Pair<String, String> myPair = new Pair<>("Admin", "123456");
        System.out.println("Key is: " + myPair.getKey());
    }

    // 使用 K 和 V 作为类型的占位符（代表 Key 和 Value）
    public static class Pair<K, V>{
        private K key;
        private V value;

        public Pair(K  key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
