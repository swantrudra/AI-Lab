import java.util.*;

public class FamilyTreeParser {

    // Store parent -> list of children
    private static Map<String, List<String>> familyTree = new HashMap<>();

    // Add parent-child relationship to knowledge base
    public static void addParent(String parent, String child) {
        familyTree.putIfAbsent(parent, new ArrayList<>());
        familyTree.get(parent).add(child);
    }

    // Get children of a person
    public static List<String> getChildren(String parent) {
        return familyTree.getOrDefault(parent, new ArrayList<>());
    }

    // Get parents of a person
    public static List<String> getParents(String child) {
        List<String> parents = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : familyTree.entrySet()) {
            if (entry.getValue().contains(child)) {
                parents.add(entry.getKey());
            }
        }
        return parents;
    }

    // Check if two people are siblings
    public static boolean areSiblings(String person1, String person2) {
        for (String parent : familyTree.keySet()) {
            List<String> children = familyTree.get(parent);
            if (children.contains(person1) && children.contains(person2)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Knowledge base
        addParent("John", "Mike");
        addParent("John", "Sarah");
        addParent("Mary", "Mike");
        addParent("Mary", "Sarah");
        addParent("Mike", "David");

        System.out.println("Children of John: " + getChildren("John"));
        System.out.println("Parents of Sarah: " + getParents("Sarah"));
        System.out.println("Are Mike and Sarah siblings? " + areSiblings("Mike", "Sarah"));
        System.out.println("Are Sarah and David siblings? " + areSiblings("Sarah", "David"));
    }
}
