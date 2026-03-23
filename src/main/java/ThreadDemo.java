import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadDemo {
    // 这是一个会产生竞态条件（漏洞）的普通变量
    private static int unsafeCounter = 0;

    // 这是一个线程安全的计数器（修复方案）
    private static AtomicInteger safeCounter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- 第一部分：线程创建的三种方式 ---");

        // 方式 1：继承 Thread 类 (匿名内部类写法)
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                System.out.println("[Thread 1] 我是继承 Thread 类创建的打工人。");
            }
        };
        thread1.start();

        // 方式 2：实现 Runnable 接口
        Thread thread2 = new Thread(() -> {
            System.out.println("[Thread 2] 我是实现 Runnable 接口创建的打工人。");
        });
        thread2.start();

        // 方式 3：使用线程池 (在写目录扫描器、并发爆破工具时最常用)
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(() -> System.out.println("[Thread 3] 我是线程池里的打工人。"));
        threadPool.shutdown(); // 分配完任务记得关门

        // 稍微等一下，让上面的打印先完成，免得控制台太乱
        Thread.sleep(1000);

        System.out.println("\n--- 第二部分：演示竞争条件漏洞 ---");

        // 我们雇佣 10 个线程，同时去执行累加任务！
        // Thread[] hackers = new Thread[10];

        // for (int i = 0; i < 10; i++) {
            //hackers[i] = new Thread(() -> {
                // 每个线程疯狂执行 10000 次加法
                //for (int j = 0; j < 10000; j++) {
                    //unsafeCounter++;
                    //safeCounter.incrementAndGet(); // 这是一个原子操作，绝对安全
                //}
            //});
            // hackers[i].start();
        // }

        // 主线程必须等这 10 个干活的线程全跑完（调用 join 方法）
        // for (int i = 0; i < 10; i++) {
            // hackers[i].join();
        // }

        // 使用线程池实现
//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//
//        for(int i = 0; i < 10; i++){
//            executorService.execute(() -> { // 已经开始执行了
//                for (int j = 0; j < 100000; j++) {
//                    unsafeCounter++;
//                    safeCounter.incrementAndGet();
//                }
//            });
//        };
//
//        executorService.shutdown(); // 停止接收任务
//
//        try {
//            executorService.awaitTermination(1, java.util.concurrent.TimeUnit.MINUTES);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // 使用Runnable实现
        Thread[] hackers = new Thread[100];

        for  (int i = 0; i < hackers.length; i++) {
            Runnable payloadTask = () -> {
                for (int j = 0; j < 100000; j++) {
                    unsafeCounter++;
                    safeCounter.incrementAndGet();
                }
            };

            hackers[i] = new Thread(payloadTask);

            hackers[i].start();
        }

        for (int i = 0; i < hackers.length; i++) {
            hackers[i].join();
        }

        // 揭晓答案时刻！
        System.out.println("预期结果: 100 * 100000 = 10000000");
        System.out.println("不安全的计数器 (漏洞现场): " + unsafeCounter);
        System.out.println("安全的计数器 (Atomic修复): " + safeCounter.get());
    }
}