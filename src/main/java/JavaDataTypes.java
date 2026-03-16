/**
 * JavaDataTypes.java
 * 展示 Java 中的所有基本数据类型及其属性
 */
public class JavaDataTypes {

    public static void main(String[] args) {
        // --- 1. 整数类型 ---

        // byte: 8位，有符号
        byte minByte = Byte.MIN_VALUE; // -128
        byte maxByte = Byte.MAX_VALUE; // 127

        // short: 16位，有符号
        short minShort = Short.MIN_VALUE; // -32768
        short maxShort = Short.MAX_VALUE; // 32767

        // int: 32位，有符号（渗透测试中常关注整数溢出点）
        int minInt = Integer.MIN_VALUE;
        int maxInt = Integer.MAX_VALUE;

        // long: 64位，有符号
        long minLong = Long.MIN_VALUE;
        long maxLong = Long.MAX_VALUE;

        // --- 2. 浮点类型 ---

        // float: 32位，单精度
        float minFloat = Float.MIN_VALUE;
        float maxFloat = Float.MAX_VALUE;

        // double: 64位，双精度
        double minDouble = Double.MIN_VALUE;
        double maxDouble = Double.MAX_VALUE;

        // --- 3. 字符类型 ---

        // char: 16位，存储 Unicode 字符
        char letterA = 'A';
        char unicodeChar = '\u4e2d'; // '中'

        // --- 4. 布尔类型 ---

        // boolean: 只有 true 和 false
        boolean isSafe = true;

        // 基本类型（值类型）vs 包装类（引用类型）
        int primitive = 42;
        Integer boxed = 42;           // 自动装箱
        int unboxed = boxed;          // 自动拆箱
        System.out.println(primitive == boxed);   // true（值比较）
        Integer a = 128, b = 128;
        System.out.println(a == b);   // false！超出缓存范围，引用不同
        System.out.println(a.equals(b)); // true（内容比较）

        // --- 打印输出 ---
        System.out.println("=== Java 整数类型 ===");
        System.out.printf("Byte: %d 到 %d%n", minByte, maxByte);
        System.out.printf("Short: %d 到 %d%n", minShort, maxShort);
        System.out.printf("Int: %d 到 %d%n", minInt, maxInt);
        System.out.printf("Long: %d 到 %d%n", minLong, maxLong);

        System.out.println("\n=== Java 浮点类型 ===");
        System.out.printf("Float Max: %e%n", maxFloat);
        System.out.printf("Double Max: %e%n", maxDouble);

        System.out.println("\n=== 其他类型 ===");
        System.out.printf("Char 示例: %c (Unicode: %s)%n", letterA, "U+4E2D");
        System.out.printf("转换ASCII测试： %d%n", (int) letterA);
        System.out.printf("Boolean 值: %b%n", isSafe);
    }
}