public class AccessControlDemo {
    public static void main(String[] args) {
        User Xiaowang = new User(Boolean.TRUE, "Xiaowang");
        System.out.println(Xiaowang.toString());
        // System.out.println(Xiaowang.name); // Cause Error
    }
}
