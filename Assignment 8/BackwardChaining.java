import java.util.*;

class Rule {
    List<String> conditions;
    String conclusion;

    Rule(List<String> conditions, String conclusion) {
        this.conditions = conditions;
        this.conclusion = conclusion;
    }
}

public class BackwardChaining {

    static Set<String> facts = new HashSet<>();
    static List<Rule> rules = new ArrayList<>();

    // Recursive backward chaining function
    static boolean prove(String goal, Set<String> visited) {
        if (facts.contains(goal)) {
            return true; // Goal is already known
        }

        if (visited.contains(goal)) {
            return false; // Avoid infinite loops
        }

        visited.add(goal);

        for (Rule rule : rules) {
            if (rule.conclusion.equals(goal)) {
                boolean allConditionsTrue = true;

                for (String condition : rule.conditions) {
                    if (!prove(condition, visited)) {
                        allConditionsTrue = false;
                        break;
                    }
                }

                if (allConditionsTrue) {
                    facts.add(goal); // Add proven fact
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // --- Step 1: Knowledge Base (Facts) ---
        facts.addAll(Arrays.asList("A", "B"));

        // --- Step 2: Rules ---
        rules.add(new Rule(Arrays.asList("A", "B"), "C"));
        rules.add(new Rule(Arrays.asList("C", "D"), "E"));
        rules.add(new Rule(Arrays.asList("B"), "D"));

        String goal = "E";
        System.out.println("Initial facts: " + facts);

        boolean result = prove(goal, new HashSet<>());

        if (result) {
            System.out.println("Goal " + goal + " PROVEN ");
            System.out.println("Updated facts: " + facts);
        } else {
            System.out.println("Goal " + goal + " CANNOT be proven");
        }
    }
}

