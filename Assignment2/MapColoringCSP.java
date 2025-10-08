import java.util.*;

public class MapColoringCSP {

    // Variables (regions to color)
    static String[] variables = {"WA", "NT", "SA", "Q", "NSW", "V", "T"};

    // Domain (available colors)
    static String[] colors = {"Red", "Green", "Blue"};

    // Adjacency map (constraints: no two adjacent regions have the same color)
    static Map<String, List<String>> adjacency = new HashMap<>();

    // Assignment of colors to regions
    static Map<String, String> assignment = new HashMap<>();

    public static void main(String[] args) {
        // Define adjacency constraints
        adjacency.put("WA", Arrays.asList("NT", "SA"));
        adjacency.put("NT", Arrays.asList("WA", "SA", "Q"));
        adjacency.put("SA", Arrays.asList("WA", "NT", "Q", "NSW", "V"));
        adjacency.put("Q", Arrays.asList("NT", "SA", "NSW"));
        adjacency.put("NSW", Arrays.asList("Q", "SA", "V"));
        adjacency.put("V", Arrays.asList("SA", "NSW", "T"));
        adjacency.put("T", Arrays.asList("V"));

        // Solve the CSP
        boolean solved = backtrack();

        // Display result
        if (solved) {
            System.out.println("Solution found:");
            for (String var : variables) {
                System.out.println(var + " = " + assignment.get(var));
            }
        } else {
            System.out.println("No solution found.");
        }
    }

    
    static boolean backtrack() {
        
        if (assignment.size() == variables.length) {
            return true;
        }

        String var = selectUnassignedVariable();

        
        for (String color : colors) {
            if (isConsistent(var, color)) {
                
                assignment.put(var, color);

                
                if (backtrack()) {
                    return true;
                }

                
                assignment.remove(var);
            }
        }
        return false; 
    }


    static String selectUnassignedVariable() {
        for (String var : variables) {
            if (!assignment.containsKey(var)) {
                return var;
            }
        }
        return null;
    }

    
    static boolean isConsistent(String var, String color) {
        for (String neighbor : adjacency.get(var)) {
            if (color.equals(assignment.get(neighbor))) {
                return false; 
            }
        }
        return true;
    }
}
