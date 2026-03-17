public class GoldenRetriever extends Dog {
    public GoldenRetriever(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " I'm a GoldenRetriever!~~~");
    }
}
