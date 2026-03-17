abstract class Animal {
    protected String name;
    public Animal(String name) { this.name = name; }

    public abstract void makeSound();

    public void breathe() {
        System.out.println(name + " is breathing");
    }
}