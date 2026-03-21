package past;

import java.util.*;

public class IteratorDemo {
    public static void main(String[] args) {
        // Key 是漏洞名，Value 是对应的对象（这里用 Object，模拟 Gadget 链的灵活性）
        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("Shiro-550", "AES Key RememberMe");
        payloadMap.put("Fastjson-1.2.24", "TemplatesImpl Cache");
        payloadMap.put("Log4Shell", "JNDI Lookup");

        System.out.println("--- 开始迭代攻击上下文 ---");

        // 获取EntrySet的迭代器
        // 想象一下，CC1 链就是在这里开始“搞事情”的
        //Iterator<Map.Entry<String, Object>> iterator = payloadMap.entrySet().iterator();

        // 使用 while 循环配合 hasNext() 遍历
        //while (iterator.hasNext()) {
            //Map.Entry<String, Object> entry = iterator.next();

            //String vulnName = entry.getKey();
            //Object gadget = entry.getValue();

            // 模拟触发点：
            // 在实际漏洞中，只要这里调用了 gadget.toString() 或某些方法，
            // 如果 gadget 是个被伪装的恶意对象，就会爆炸。
            //System.out.println("[Checking] 漏洞: " + vulnName + " | 载荷: " + gadget);
        //}

        // Map 的 forEach 支持同时传入 key 和 value 两个参数
        payloadMap.forEach((vulnName,gadget) -> {
            System.out.println("[Checking] 漏洞: " + vulnName + " | 载荷: " + gadget);
        });


        /*  for (Map.Entry<String, Object> entry : payloadMap.entrySet()){
        *       String vulnName = entry.getKey();
        *       Object gadget = entry.getValue();
        *       System.out.println("[Checking] 漏洞: " + vulnName + " | 载荷: " + gadget);
        *   }
        *
        * */

        System.out.println("--- 迭代完成 ---");
    }
}