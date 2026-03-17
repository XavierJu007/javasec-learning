import java.util.concurrent.Callable;

public class InheritanceDemo {
    public static void main(String[] args) {
        Animal xiaofu = new Cat("xiaofu");

        Animal daxiong = new GoldenRetriever("sofree");

        daxiong.makeSound();
        xiaofu.makeSound();

        xiaofu.breathe();
        daxiong.breathe();
    }

}
