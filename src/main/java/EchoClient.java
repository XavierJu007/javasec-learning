import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        System.out.println("[Client] 准备拨号连接服务端...");

        // 1. 主动拨号：连接本机 (127.0.0.1) 的 9999 端口
        // 只要这行代码没报错，就说明电话打通了！
        Socket socket = new Socket("127.0.0.1", 9999);
        System.out.println("[Client] 连接成功！可以开始发消息了 (输入 bye 退出)。");

        // 2. 拿到话筒和听筒
        // 话筒：发给服务端
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // 听筒：听服务端的回信
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // 3. 准备一个键盘扫描器，读取你在控制台敲的字
        Scanner scanner = new Scanner(System.in);

        // 4. 开始聊天
        while (true) {
            System.out.print("请输入内容 > ");
            String myMsg = scanner.nextLine(); // 读取你的键盘输入

            // 对着话筒喊话（发给服务端）
            out.println(myMsg);

            // 把听筒贴在耳朵上听（读取服务端的回信）
            String response = in.readLine();
            System.out.println("服务端回复: " + response);

            if ("bye".equalsIgnoreCase(myMsg)) {
                break;
            }
        }

        // 5. 挂断电话
        socket.close();
        scanner.close();
        System.out.println("[Client] 客户端已退出。");
    }
}