/*
背景：
你写了一个 WAF（Web 应用防火墙），抓到了一批访问日志。
现在你需要用 Stream API 把包含恶意特征（比如 whoami 或 ../）的日志提取出来，
打上 [ALERT] 标签，并收集成一个新的 List。
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WafDemo {
    public static void main(String[] args) {
        List<String> accessLogs = Arrays.asList(
                "GET /index.html 200",
                "GET /login?cmd=whoami 200",
                "POST /upload.jsp 200",
                "GET /static/../../etc/passwd 403",
                "GET /css/style.css 200"
        );

        // TODO: 开启流水线
        List<String> maliciousLogs = accessLogs.stream()
                // 1. 过滤：只保留包含 "whoami" 或者包含 "../" 的日志
                .filter(log -> log.contains("whoami") || log.contains("../"))
                // 2. 映射：在保留下来的日志前面拼接 "[ALERT] 发现攻击: "
                .map(log -> "[ALERT] " + log)
                // 3. 收集：打包成 List
                .collect(Collectors.toList());

        System.out.println("拦截到的攻击请求：");
        maliciousLogs.forEach(System.out::println);
    }
}