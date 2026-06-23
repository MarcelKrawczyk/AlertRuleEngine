public class AlertRuleEngine {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        for (int value = 1; value <= 20; value++) {
            System.out.println(classify(value));
        }
    }

    private static String classify(int value) {
        if (value % 3 == 0 && value % 5 == 0) {
            return "LOWADVISORY";
        } else if (value % 3 == 0) {
            return "LOW";
        } else if (value % 5 == 0) {
            return "ADVISORY";
        } else {
            return String.valueOf(value);
        }
    }
}