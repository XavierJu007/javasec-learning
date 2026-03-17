/*
* 简易暴力破解检测器 (Brute-Force Detector)
* 实践一下循环什么的，没有细化功能
* */


import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class ControlFlow {
    private int status;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Map<String, String> users = new HashMap<>();
        int attemptCount = 0;

        while(attemptCount < 5){
            System.out.printf("Welcome to Winner System!%n");
            System.out.printf("Make your choice!%n");
            System.out.printf("1. LOGIN%n");
            System.out.printf("2. REGISTER%n");
            System.out.printf("3. EXIT%n");

            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            switch(choice) {
                case 1:
                    System.out.print("Enter your username: ");
                    String name = sc.next();
                    System.out.print("Enter your password: ");
                    String pass = sc.next();
                    if (users.containsKey(name)) {
                        if (users.get(name).equals(pass)) {
                            System.out.println("You Login!");
                            attemptCount = 0;
                        } else {
                            System.out.println("Wrong password!");
                            attemptCount++;
                        }
                    } else {
                        System.out.println("User name not found! Please register first!");
                    }
                    break;
                case 2:
                    System.out.print("Enter your username: ");
                    String username = sc.next();
                    System.out.print("Enter your password: ");
                    String password = sc.next();
                    if (users.containsKey(username)) {
                        System.out.println("Username Already Exist!");
                    } else {
                        users.put(username, password);
                        System.out.println("You Register Successfully!");
                    }
                    break;
                case 3:
                    System.out.print("Exiting System!");
                    System.exit(0);
                default:
                    continue;

            }
        }
    }
}
