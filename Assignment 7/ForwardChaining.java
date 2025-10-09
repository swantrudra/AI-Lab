import java.util.*;

class Rule {
    List<String> conditions;
    String conclusion;

    Rule(List<String> conditions, String conclusion) {
        this.conditions = conditions;
        this.conclusion = conclusion;
    }

    boolean canFire(Set<String> facts) {
        // Rule fires only if all conditions are in facts
        return facts.containsAll(conditions);
    }
}

public class ForwardChaining {

    public static void main(String[] args) {
        // --- Step 1: Knowledge Base (Facts) ---
        Set<String> facts = new HashSet<>(Arrays.asList("A", "B"));

        // --- Step 2: Rules ---
        List<Rule> rules = new ArrayList<>();
        rules.add(new Rule(Arrays.asList("A", "B"), "C"));
        rules.add(new Rule(Arrays.asList("C", "D"), "E"));
        rules.add(new Rule(Arrays.asList("B"), "D"));

        String goal = "E"; // we want to derive E
        System.out.println("Initial facts: " + facts);

        boolean newFactAdded;
        boolean goalReached = false;

        do {
            newFactAdded = false;

            for (Rule rule : rules) {
                if (rule.canFire(facts) && !facts.contains(rule.conclusion)) {
                    System.out.println("Rule fired: " + rule.conditions + " => " + rule.conclusion);
                    facts.add(rule.conclusion);
                    newFactAdded = true;

                    if (rule.conclusion.equals(goal)) {
                        goalReached = true;
                        break;
                    }
                }
            }
        } while (newFactAdded && !goalReached);

        System.out.println("Final facts: " + facts);
        System.out.println("Goal " + goal + (goalReached ? " REACHED " : " NOT reached "));
    }
}
 