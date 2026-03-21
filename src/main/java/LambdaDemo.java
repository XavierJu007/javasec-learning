import java.util.*;

public class LambdaDemo {
    public static void main(String[] args) {
        // Lambda 写法：Thread 的构造函数只需要一个 Runnable，而 Runnable 只有一个 run() 方法。
        // 所以直接把逻辑扔进去！
        new Thread(() -> System.out.println("开始执行端口扫描任务...")).start();

        /* 以前的旧写法：为了传一段执行逻辑，要 new 一个匿名类，非常臃肿
        * new Thread(new Runnable() {
        *   @Override
        *   public void run() {
        *       System.out.println("开始执行端口扫描任务...");
        *   }
         * }).start();
        * */


        List<String> payloads = Arrays.asList("calc.exe", "ping -c 1 127.0.0.1", "whoami");

        // Comparator 接口的核心方法是 compare(T o1, T o2)
        // Lambda 写法：传入两个参数 p1 和 p2，返回它们的长度差
        payloads.sort((p1, p2) -> p1.length() - p2.length());

        System.out.println("按长度排序后： " + payloads);
    }
}



