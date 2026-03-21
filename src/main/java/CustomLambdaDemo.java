public class CustomLambdaDemo {
    // 1. 定义一个只有【一个方法】的接口（加不加 @FunctionalInterface 注解都可以，加了能让编译器帮你检查）
    @FunctionalInterface
    interface Exploit{
        void attack(String targetUrl);
    }

    public static void main(String[] args) {
        // 2. 用 Lambda 实例化这个接口！
        // 因为 Exploit 只有一个 attack 方法接收 String，所以 target 就是那个 String
        Exploit sqlInject = target -> System.out.println("向 "+ target + " 发送 SQL 注入 Payload: ' OR 1=1 --");
        Exploit xssAttack = target -> System.out.println("向 " + target + " 注入弹窗脚本: <script>alert(1)</script>");

        // 3. 调用
        sqlInject.attack("http://admin.com/login");
        xssAttack.attack("http://admin.com/comment");
    }


}
