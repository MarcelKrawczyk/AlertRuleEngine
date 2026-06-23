import java.util.List;

public class AlertRuleEngine {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        RuleEngine ruleEngine = new RuleEngine(List.of(
                new DivisibilityRule(3, "LOW"),
                new DivisibilityRule(5, "ADVISORY"),
                new DivisibilityRule(7, "WARN")
        ));
        for (int value = 1; value <= 20; value++){
            System.out.println(ruleEngine.process(value));
        }
        System.out.println(ruleEngine.process(21));
        System.out.println(ruleEngine.process(35));
        System.out.println(ruleEngine.process(105));
    }
}