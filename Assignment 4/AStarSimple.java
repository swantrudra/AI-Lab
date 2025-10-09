import java.util.*;

// Node class representing each cell in the grid
class Node implements Comparable<Node> {
    int row, col;
    int gCost, hCost, fCost; // g = cost from start, h = heuristic, f = g+h
    Node parent;

    Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Node other) {
        return this.fCost - other.fCost;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) return false;
        Node other = (Node) obj;
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

public class AStarSimple {
    static final int ROWS = 5;
    static final int COLS = 5;
    static boolean[][] obstacleGrid = {
        { false, false, false, false, false },
        { false, true,  true,  false, false },
        { false, false, false, true,  false },
        { false, true,  false, false, false },
        { false, false, false, false, false }
    };

    static Node start = new Node(0, 0);
    static Node goal = new Node(4, 4);

    public static void main(String[] args) {
        List<Node> path = aStarSearch(start, goal);
        if (path != null) {
            System.out.println("Path found:");
            for (Node n : path) {
                System.out.println("(" + n.row + ", " + n.col + ")");
            }
        } else {
            System.out.println("No path found!");
        }
    }

    static List<Node> aStarSearch(Node start, Node goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();

        start.gCost = 0;
        start.hCost = heuristic(start, goal);
        start.fCost = start.gCost + start.hCost;
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(goal)) {
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (Node neighbor : getNeighbors(current)) {
                if (closedSet.contains(neighbor)) continue;

                int tentativeG = current.gCost + 1; // cost between adjacent cells = 1

                boolean betterPath = false;
                if (!openSet.contains(neighbor)) {
                    betterPath = true;
                    neighbor.hCost = heuristic(neighbor, goal);
                    openSet.add(neighbor);
                } else if (tentativeG < neighbor.gCost) {
                    betterPath = true;
                }

                if (betterPath) {
                    neighbor.parent = current;
                    neighbor.gCost = tentativeG;
                    neighbor.fCost = neighbor.gCost + neighbor.hCost;
                }
            }
        }

        return null; // no path
    }

    static int heuristic(Node a, Node b) {
        // Manhattan distance
        return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
    }

    static List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[][] directions = { {1,0}, {-1,0}, {0,1}, {0,-1} };

        for (int[] d : directions) {
            int newRow = node.row + d[0];
            int newCol = node.col + d[1];

            if (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS && !obstacleGrid[newRow][newCol]) {
                neighbors.add(new Node(newRow, newCol));
            }
        }
        return neighbors;
    }

    static List<Node> reconstructPath(Node current) {
        List<Node> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }
}
