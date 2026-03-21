package past;

import java.util.*;

public class VulnManager {
    public static void main(String[] args) {
        // 1. 泛型的力量：创建一个只能存 String 的 List
        // 如果不加 <String>，这就是一个“脏”容器，什么都能往里塞
        List<String> cveList = new ArrayList<>();
        cveList.add("CVE-2021-44228"); // Log4Shell
        cveList.add("CVE-2022-22965"); // Spring4Shell

        // 尝试取消下面这行的注释，看看编译器会不会报错？
        // cveList.add(12345);

        System.out.println("--- 当前监控的 CVE 列表 ---");
        cveList.forEach(cve -> System.out.println("监控中： " + cve));

        // 2. Map 的实战：建立 漏洞名 -> CVE编号 的映射
        // <K, V> 泛型：Key 是 String，Value 也是 String
        Map<String, String> vulnDb = new HashMap<>();
        vulnDb.put("Log4Shell", "CVE-2021-44228");
        vulnDb.put("DirtyPipe", "CVE-2022-0847");

        System.out.println("\n--- 漏洞库检索 ---");
        String target = "Log4Shell";
        if (vulnDb.containsKey(target)) {
            System.out.println("找到 " + target + " 的编号: " + vulnDb.get(target));
        }
    }
}