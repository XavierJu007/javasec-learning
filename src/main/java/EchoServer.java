import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        // 1. 建立基站：监听本机的 9999 端口
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("[Server] 服务端已启动，正在 9999 端口死等客户端连接...");

        // 2. 阻塞等待：程序会卡在这里，直到有客户端拨通 9999 端口！
        Socket clientSocket = serverSocket.accept();
        System.out.println("[Server] 叮！有客户端连进来了！IP: " + clientSocket.getInetAddress());

        // 3. 拿到话筒和听筒
        // 听筒 (InputStream)：用来读取客户端发来的消息
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // 话筒 (OutputStream)：用来给客户端回消息 (true 表示自动刷新缓冲区，不用手动 flush)
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        // 4. 开始循环对话
        String line;
        // readLine() 也是阻塞的，客户端不发消息，它就一直等
        while ((line = in.readLine()) != null) {
            System.out.println("[Server] 收到客户端消息: " + line);
            // 给客户端回信
            out.println("[ECHO] " + line);

            // 如果收到 bye，就挂断电话
            if ("bye".equalsIgnoreCase(line)) {
                break;
            }
        }

        // 5. 挂断电话，打烊
        System.out.println("[Server] 连接断开，服务端关闭。");
        clientSocket.close();
        serverSocket.close();
    }
}