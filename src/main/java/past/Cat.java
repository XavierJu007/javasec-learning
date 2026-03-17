public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name+": Meow!");
    }

    public void purr() {
        System.out.println(name + " is purring");
    }
}
