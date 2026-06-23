import java.util.List;

public class RuleEngine {
    private final List<AlertRule> rules;

    public RuleEngine(List<AlertRule> rules) {
        this.rules = rules;
    }
    public String process(int value) {
        StringBuilder result = new StringBuilder();
        for (AlertRule rule : rules) {
            result.append(rule.evaluate(value));
        }
        return !result.isEmpty() ? result.toString() : String.valueOf(value);
    }
}