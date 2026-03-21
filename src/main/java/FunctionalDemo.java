import java.util.function.Function;

public class FunctionalDemo {
    public static void main(String[] args) {
        // 我们来定义三个“微小而无害”的加工规则（Function）

        // 规则 1：去除首尾空格（这里用了你笔记里的“方法引用”）
        Function<String, String> trimFunc = String::trim;

        // 规则 2：转换大写（方法引用）
        Function<String, String> upperFunc = String::toUpperCase;

        // 规则 3：拼接前缀（普通的 Lambda，就是你刚刚在 map 里写对的那个）
        Function<String, String> addPrefixFunc = s -> "CVE-" + s;

        // 【高能预警：组装攻击链（Gadget Chain）】
        // 利用 andThen 把三个规则像锁链一样扣在一起！
        // 执行顺序：先 trim，然后 upper，最后 addPrefix
        Function<String, String> exploitChain = trimFunc.andThen(upperFunc).andThen(addPrefixFunc);

        // 准备一个脏数据（模拟黑客传入的 Payload 雏形）
        String dirtyPayload = "  2021-44228  ";

        System.out.println("原始输入: [" + dirtyPayload + "]");

        // 触发这根链条！调用 apply() 方法，把数据塞进链条的入口
        String result = exploitChain.apply(dirtyPayload);

        System.out.println("经过链条处理: [" + result + "]");
    }
}