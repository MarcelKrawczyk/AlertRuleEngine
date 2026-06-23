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
        StringBuilder result = new StringBuilder();

        if (value % 3 == 0){
            result.append("LOW");
        }
        if (value % 5 == 0){
            result.append("ADVISORY");
        }
        return !result.isEmpty() ? result.toString() : String.valueOf(value);
    }
}