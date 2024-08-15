import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int minMoves(int[][] rooks) {
        // Assign each rook a column in order of their current column values
        // Sort rooks by input column
        // Put the first one in column 0, the second in column 1, etc.
        // Add the absolute difference between target and original values to output
        // Repeat for rows
        
        int moves = 0;

        // Rows
        Arrays.sort(rooks, Comparator.comparingInt(r -> r[0]));
        for (int row = 0; row < rooks.length; row++) {
            moves += Math.abs(row - rooks[row][0]);
        }

        // Columns
        Arrays.sort(rooks, Comparator.comparingInt(r -> r[1]));
        for (int col = 0; col < rooks.length; col++) {
            moves += Math.abs(col - rooks[col][1]);
        }
        
        return moves;
    }
}