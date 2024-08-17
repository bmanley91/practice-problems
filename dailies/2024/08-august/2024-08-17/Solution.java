class Solution {
    public long maxPoints(int[][] points) {
        // 2D dynamic programming
        // Find the max score we could possibly have at each cell
        // For each cell in a given row, look at the max score possible by coming from each cell in the row above
        // Return the max value in the bottom row
        long[][] maxPoints = new long[points.length][points[0].length];

        for (int row = 0; row < points.length; row++) {
            for (int col = 0; col < points[row].length; col++) {
                // Special case for top row
                if (row == 0) {
                    maxPoints[row][col] = points[row][col];
                } else {
                    // Check each col in the DP grid above this one to see what the max 
                    // value we can get by moving into the current cell
                    // Only look at most points[row][col] many rows to the left and right of the current row
                    int left = Math.max(0, col - points[row][col] - 1);
                    int right = Math.min(points[row].length - 1, col + points[row][col] + 1);
                    for (int colAbove = left; colAbove <= right; colAbove++) {
                        maxPoints[row][col] = Math.max(
                            maxPoints[row][col],
                            maxPoints[row-1][colAbove] + points[row][col] - Math.abs(col - colAbove)
                        );
                    }
                }
            }
        }

        // Get the max val from the last row in the DP grid
        long output = 0;
        for (int col = 0; col < maxPoints[maxPoints.length - 1].length; col++) {
            output = Math.max(
                output,
                maxPoints[maxPoints.length-1][col]
            );
        }
        return output;
    }
}
