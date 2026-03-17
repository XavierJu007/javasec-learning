import java.util.*;

public class LinkedListDemo {
    public static void main(String[] args) {
        // 使用 Object，这样我们可以存入不同类型的“零件”
        List<Object> gadgetChain = new LinkedList<>();

        // 1. 存入一个字符串（命令）
        gadgetChain.add("calc.exe");

        // 2. 存入一个 Integer（模拟某个溢出参数）
        gadgetChain.add(1337);

        // 3. 存入另一个集合（嵌套，Gadget 链常见操作）
        Map<String, String> innerMap = new HashMap<>();
        innerMap.put("trigger", "readObject");
        gadgetChain.add(innerMap);

        System.out.println("链表长度: " + gadgetChain.size());

        // 遍历时，因为是 Object，我们需要知道它到底是什么（或者直接用 Object 处理）
        for (Object obj : gadgetChain) {
            System.out.println("节点数据类型: " + obj.getClass().getName() + " | 值: " + obj);
        }
    }
}