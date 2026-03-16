public class InsufficientBalanceException extends Exception {
    private final double shortage;

    public InsufficientBalanceException(String message, double shortage) {
        super(message);
        this.shortage = shortage;
    }

    public double getShortage() {
        return shortage;
    }

    public String getFormattedShortage() {
        return String.format("%.2f", shortage);  // 给展示用
    }
}
