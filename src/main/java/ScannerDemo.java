import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScannerDemo {


    // TODO 1: 在这里定义一个名为 VulnCallback 的函数式接口
    // 要求：里面只有一个抽象方法 void onFound(String url, String payload);
    // 提示：可以加上 @FunctionalInterface 注解
    @FunctionalInterface
    public interface VulnCallback {
        void onFound(String url, String payload);
    }


    // 这是一个模拟扫描的方法（不用改）
    public static void startScan(String target, VulnCallback callback) {
        System.out.println("正在扫描: " + target + " ...");
        // 模拟发现漏洞
        List<String> payloads = Arrays.asList("whoami", "calc.exe", "ping");

        // 正则提取所有参数值，例如 ?cmd=whoami&foo=bar 提取出 whoami 和 bar
        Pattern pattern = Pattern.compile("[?&][^=]+=([^&]+)");
        Matcher matcher = pattern.matcher(target);


        while (matcher.find()) {
            String paramValue = matcher.group(1);
            if (payloads.contains(paramValue)) {
                callback.onFound(target, paramValue);
            }
        }
    }

    public static void main(String[] args) {
        List<String> scanList = Arrays.asList(
                "http://testphp.vulnweb.com/listproducts.php?cat=1",
                "http://testphp.vulnweb.com/exec?cmd=whoami",
                "http://testphp.vulnweb.com/exec?cmd=ping&host=127.0.0.1",
                "http://testphp.vulnweb.com/exec?cmd=calc.exe",
                "http://testphp.vulnweb.com/listproducts.php?cat=5"
        );

        // TODO 2: 调用 startScan 方法，用 Lambda 表达式作为第二个参数！
        // 逻辑：当发现漏洞时，在控制台打印 "警告！在 [url] 处发现漏洞，使用的 payload 是: [payload]"
        scanList.forEach(scanUrl -> startScan(scanUrl, (target, payload) -> System.out.println("警告！在 [" + target + "] 处发现漏洞，使用的 payload 是: [" + payload +"]")));
        // startScan("http://testphp.vulnweb.com/listproducts.php?cat=1", (target, payload) -> System.out.println("警告！在 [" + target + "] 处发现漏洞，使用的 payload 是: [" + payload +"]"));
    }
}