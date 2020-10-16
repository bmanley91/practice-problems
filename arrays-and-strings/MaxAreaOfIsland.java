// https://leetcode.com/problems/max-area-of-island/
class Solution {
    boolean[][] visited;
    
    public int maxAreaOfIsland(int[][] grid) {
        visited = new boolean[grid.length][grid[0].length];
        int maxCount = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[0].length; column++) {
                maxCount = Math.max(maxCount, areaAroundCell(grid, row, column));
            }
        }
        return maxCount;
    }
    
    private int areaAroundCell(int[][] grid, int row, int column) {
        if (row < 0 || row >= grid.length ||            // If the row went out of bounds
            column < 0 || column >= grid[0].length ||   // Or the column went out of bounds
            visited[row][column] ||                     // Or we already visited the current cell
            grid[row][column] == 0) {                   // Or there's not land at this position:
            // Then stop searching.
            return 0;
        }
        
        visited[row][column] = true;
        return (1 +             // Return the current cell plus the following:
            areaAroundCell(grid, row + 1, column) +     // North recursive call
            areaAroundCell(grid, row - 1, column) +     // South recursive call
            areaAroundCell(grid, row, column - 1) +     // West recursive call
            areaAroundCell(grid, row, column + 1)       // East recursive call
       );
    }
}