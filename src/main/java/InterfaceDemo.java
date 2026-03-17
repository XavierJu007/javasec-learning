public class InterfaceDemo {
    public interface Transformer{
        Object transform(Object input);
    }

    public class UpperCaseTransformer implements Transformer {
        @Override
        public Object transform(Object input) {
            return input.toString().toUpperCase();
        }
    }

    public class LowerCaseTransformer implements Transformer {
        @Override
        public Object transform(Object input) {
            return input.toString().toLowerCase();
        }
    }

    private static String password = "Acsds";

    public static void main(String[] args) {
        System.out.println(password.toLowerCase());
        System.out.println(password.toUpperCase());
    }

}
