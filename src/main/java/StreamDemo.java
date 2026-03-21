import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class StreamDemo {
    public static void main(String[] args) {
        List<String> cves = Arrays.asList(
                "cve-2021-44228", "CVE-2022-22965", "cve-2017-5638",
                "CVE-2019-0708", "cve-2021-26855"
        );

        System.out.println("清洗前的数据: " + cves);

        // 2. 开启流水线作业 (Stream API)
        List<String> result = cves.stream()          // 第一步：把集合的数据倒上流水线
                .filter(cve -> cve.contains("2021"))     // 第二步：安检员 (Predicate接口) -> 只要包含 "2021" 的
                .map(String::toUpperCase)                   // 第三步：加工员 (Function接口)  -> 全部转换为大写
                .map(cve ->"[High Risk] " + cve)
                .sorted()                                // 第四步：理货员 -> 按自然顺序（字母/数字）排好队
                .collect(Collectors.toList());           // 第五步：打包员 -> 把流水线上处理好的数据装进一个新的 List 里

        // 3. 打印最终结果
        System.out.println("清洗后的数据: " + result);
    }
}
