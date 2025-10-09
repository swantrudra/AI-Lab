public class EightQueens {

    static final int N = 8; // Board size (8x8)

    // Function to print the solution
    static void printSolution(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((board[i][j] == 1 ? "Q " : ". "));
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if a queen can be placed on board[row][col]
    static boolean isSafe(int board[][], int row, int col) {
        int i, j;

        // Check column
        for (i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;

        // Check upper-left diagonal
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // Check upper-right diagonal
        for (i = row, j = col; i >= 0 && j < N; i--, j++)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    // Solve 8-Queens using backtracking
    static boolean solveNQueens(int board[][], int row) {
        // Base case: All queens placed
        if (row == N) {
            printSolution(board);
            return true; // Change to false if you want all solutions
        }

        boolean res = false;
        // Try placing queen in all columns of current row
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1; // Place queen

                // Recurse to place rest
                res = solveNQueens(board, row + 1) || res;

                board[row][col] = 0; // Backtrack
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int board[][] = new int[N][N];

        if (!solveNQueens(board, 0)) {
            System.out.println("No solution exists");
        }
    }
}
