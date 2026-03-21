import java.util.function.Function;

public class BypassDemo {
    public static void main(String[] args) {
        // 模拟从 HTTP 请求里提取出的变形 Payload (假设它混入了奇怪的符号和前缀)
        String inputPayload = "   WAF_BYPASS:whoami   ";

        // TODO 1: 定义 Function 1 - 去除前缀 "WAF_BYPASS:" (用 replace 方法)
        Function<String, String> removePrefix = payload -> payload.replace("WAF_BYPASS:", "");

        // TODO 2: 定义 Function 2 - 去除首尾的空格 (用方法引用或 Lambda)
        Function<String, String> trimSpace = String::trim;

        // TODO 3: 定义 Function 3 - 拼接上执行环境，比如 "cmd.exe /c " + 命令
        Function<String, String> addCmd = payload -> "cmd.exe /c " + payload;

        // TODO 4: 把这三个小步骤用 andThen 串起来，组合成一条完整的 exploitChain
        Function<String, String> exploitChain = removePrefix.andThen(trimSpace).andThen(addCmd);

        // 触发链条！
        String finalCommand = exploitChain.apply(inputPayload);
        System.out.println("最终将在受害者机器上执行的命令是: [" + finalCommand + "]");
        // 预期输出: [cmd.exe /c whoami]
    }
}