import java.util.*;

class Node {
    String state;
    Node parent;
    int depth;

    Node(String state, Node parent, int depth) {
        this.state = state;
        this.parent = parent;
        this.depth = depth;
    }
}

public class EightPuzzle {

    static final String GOAL = "123456780"; // Goal configuration
    static final int[][] MOVES = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static final int DFS_DEPTH_LIMIT = 20;

    static boolean isSolvable(String state) {
        String nums = state.replace("0", "");
        int inversions = 0;
        for (int i = 0; i < nums.length(); i++) {
            for (int j = i + 1; j < nums.length(); j++) {
                if (nums.charAt(i) > nums.charAt(j)) {
                    inversions++;
                }
            }
        }
        return inversions % 2 == 0; // solvable if even
    }

    static List<String> getNextStates(String state) {
        List<String> nextStates = new ArrayList<>();
        int zeroIndex = state.indexOf('0');
        int row = zeroIndex / 3;
        int col = zeroIndex % 3;

        for (int[] move : MOVES) {
            int newRow = row + move[0];
            int newCol = col + move[1];
            if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                char[] newState = state.toCharArray();
                int newIndex = newRow * 3 + newCol;
                char temp = newState[zeroIndex];
                newState[zeroIndex] = newState[newIndex];
                newState[newIndex] = temp;
                nextStates.add(new String(newState));
            }
        }
        return nextStates;
    }

    static void bfs(String start) {
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(new Node(start, null, 0));
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.state.equals(GOAL)) {
                printSolution(current);
                return;
            }
            for (String next : getNextStates(current.state)) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(new Node(next, current, current.depth + 1));
                }
            }
        }
        System.out.println("No solution found with BFS.");
    }

    static void dfs(String start) {
        Stack<Node> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        stack.push(new Node(start, null, 0));
        visited.add(start);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (current.state.equals(GOAL)) {
                printSolution(current);
                return;
            }
            if (current.depth < DFS_DEPTH_LIMIT) {
                for (String next : getNextStates(current.state)) {
                    if (!visited.contains(next)) {
                        visited.add(next);
                        stack.push(new Node(next, current, current.depth + 1));
                    }
                }
            }
        }
        System.out.println("No solution found within depth limit " + DFS_DEPTH_LIMIT);
    }

    static void printSolution(Node node) {
        List<String> path = new ArrayList<>();
        while (node != null) {
            path.add(node.state);
            node = node.parent;
        }
        Collections.reverse(path);
        for (String state : path) {
            printState(state);
        }
        System.out.println("Steps: " + (path.size() - 1));
    }

    static void printState(String state) {
        System.out.println("-------");
        for (int i = 0; i < 9; i++) {
            System.out.print((state.charAt(i) == '0' ? " " : state.charAt(i)) + " ");
            if (i % 3 == 2) System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 8-puzzle start state as 9 digits (use 0 for blank):");
        String start = sc.nextLine().trim();

        if (!isSolvable(start)) {
            System.out.println("This puzzle is NOT solvable.");
            sc.close();
            return;
        }

        System.out.println("\nBFS Solution:");
        bfs(start);

        System.out.println("\nDFS Solution (with depth limit " + DFS_DEPTH_LIMIT + "):");
        dfs(start);

        sc.close();
    }
}
