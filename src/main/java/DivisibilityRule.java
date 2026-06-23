public class DivisibilityRule implements AlertRule {
    private final int divisor;
    private final String label;

    public DivisibilityRule(int divisor, String label) {
        this.divisor = divisor;
        this.label = label;
    }

    public String evaluate(int value) {
        return value % divisor == 0 ? label : "";
    }
}