import java.util.*;

public class CollectionDemo{
    public static void main(String[] args) {
        // ArrayList 常用操作
        List<String> cves = new ArrayList<>();
        cves.add("CVE-2021-44228");
        cves.add("CVE-2022-22965");
        cves.add("CVE-2017-5638");
        System.out.println(cves.size());          // 3
        System.out.println(cves.get(0));          // CVE-2021-44228
        cves.remove("CVE-2017-5638");
        for (String cve : cves) {                 // 增强 for 循环
            System.out.println(cve);
        }

        // HashMap 常用操作
        Map<String, String> vulnMap = new HashMap<>();
        vulnMap.put("Log4Shell", "CVE-2021-44228");
        vulnMap.put("Spring4Shell", "CVE-2022-22965");
        System.out.println(vulnMap.get("Log4Shell"));
        System.out.println(vulnMap.containsKey("Heartbleed")); // false
        for (Map.Entry<String, String> entry : vulnMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}