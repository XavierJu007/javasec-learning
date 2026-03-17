/*
 * 简易银行转账验证系统 (Secure Transfer Validator)
 * 实践一下异常处理，没有细化功能
 * Mission:
 *      自定义异常类：
 *          创建一个 InsufficientBalanceException（余额不足异常）
 *          创建一个 IllegalTransactionException（非法交易异常，比如转账金额为负数，或者检测到 SQL 注入字符）
 *      Try-Catch-Finally：
 *          在 try 块里尝试执行转账逻辑（调用会抛出上述异常的方法）
 *          在 catch 块里捕获不同的自定义异常，并打印出对用户友好的（而不是泄露后台信息的）提示
 *          在 finally 块里模拟"关闭数据库连接"或"清理敏感会话数据"的操作，无论转账成功还是失败，这一步必须执行
 * */


import java.util.Scanner;

public class  ExceptionDemo {

    // 模拟账户余额
    static double accountBalance = 1000.0;

    /**
     * 输入验证：检查金额是否合法、账号是否含 SQL 注入字符
     * 如果不合法，抛出 IllegalTransactionException
     */
    static void validateInput(double amount, String toAccount) throws IllegalTransactionException {
        // 金额必须为正数
        if (amount <= 0) {
            throw new IllegalTransactionException("转账金额必须大于 0");
        }

        // 检测常见 SQL 注入字符，防止恶意输入
        String[] sqlPatterns = {"'", "\"", ";", "--", "/*", "*/"};
        for (String pattern : sqlPatterns) {
            if (toAccount.contains(pattern)) {
                throw new IllegalTransactionException("账号中包含非法字符: " + pattern);
            }
        }
    }

    /**
     * 余额检查：余额不足时抛出 InsufficientBalanceException
     */
    static void checkBalance(double amount) throws InsufficientBalanceException {
        if (amount > accountBalance) {
            throw new InsufficientBalanceException(
                "需要转账 " + amount + " 元，但余额只有 " + accountBalance + " 元",
                    amount  - accountBalance
            );
        }
    }

    /**
     * 执行转账：先验证输入，再检查余额，最后扣款
     * 方法签名上用 throws 声明可能抛出的受检异常，调用方必须处理
     */
    static void processTransfer(double amount, String toAccount)
            throws InsufficientBalanceException, IllegalTransactionException {

        validateInput(amount, toAccount);   // 可能抛 IllegalTransactionException
        checkBalance(amount);               // 可能抛 InsufficientBalanceException

        // 两项检查都通过，执行转账
        accountBalance -= amount;
        System.out.println("[成功] 已向账号 " + toAccount + " 转账 " + amount + " 元，剩余余额：" + accountBalance + " 元");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== 简易银行转账系统 ===");
        System.out.print("请输入目标账号：");
        String toAccount = sc.nextLine();

        System.out.print("请输入转账金额：");
        double amount = sc.nextDouble();

        // try-catch-finally 核心结构
        try {
            // 尝试执行转账（可能抛出自定义异常）
            processTransfer(amount, toAccount);

        } catch (IllegalTransactionException e) {
            // 捕获非法交易异常：给用户友好提示，不暴露内部实现细节
            // e.getMessage();
            // e.printStackTrace();
            System.out.println("[安全警告] 交易被拒绝：请检查输入是否合法。");

        } catch (InsufficientBalanceException e) {
            // 捕获余额不足异常
            System.out.println("[余额不足] 转账失败：您的账户余额不足以完成此次转账。" + "还差" + e.getFormattedShortage() + " 元");

        } finally {
            // 无论转账成功还是失败，finally 块必定执行
            // 模拟关闭数据库连接、清理敏感会话数据
            System.out.println("[系统] 数据库连接已关闭，敏感会话数据已清理。");
            sc.close();
        }
    }
}
