public class InterfaceDemo {
    public interface Transformer{
        Object transform(Object input);
    }

    public static class UpperCaseTransformer implements Transformer {
        @Override
        public Object transform(Object input) {
            return input.toString().toUpperCase();
        }
    }

    public static class LowerCaseTransformer implements Transformer {
        @Override
        public Object transform(Object input) {
            return input.toString().toLowerCase();
        }
    }

    public static void main(String[] args) {
        String password = "Acids"; // Just A Push Test
        System.out.println(password.toLowerCase());
        System.out.println(password.toUpperCase());
    }

}
