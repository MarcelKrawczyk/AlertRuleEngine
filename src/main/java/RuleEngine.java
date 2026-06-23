import java.util.List;

public class RuleEngine {
    private final List<DivisibilityRule> rules;

    public RuleEngine(List<DivisibilityRule> rules) {
        this.rules = rules;
    }
    public String process(int value) {
        StringBuilder result = new StringBuilder();
        for (DivisibilityRule rule : rules) {
            result.append(rule.evaluate(value));
        }
        return !result.isEmpty() ? result.toString() : String.valueOf(value);
    }
}