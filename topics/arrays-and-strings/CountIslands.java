class Solution {
    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Keep track of which cells we've visited
        boolean[][] visited = new boolean[rows][cols];
        
        int islandCount = 0;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!visited[row][col] && // If we haven't already visited this cell
                    grid[row][col] == '1' // and there's land at this cell.
                ) {
                    // Since this is unvisited land, it must be a new island
                    // so we increment the island count.
                    islandCount++;
                    
                    // Do a depth first traversal visiting adjacent land.
                    visitLandDepthFirst(row, col, grid, visited);
                }
            }
        }
        
        return islandCount;
    }
    
    private void visitLandDepthFirst(int row, int col, char[][] grid, boolean[][] visited) {
        if (row >= 0 && row < grid.length &&    // If row is in bounds
            col >= 0 && col < grid[0].length && // and col is in bounds
            !visited[row][col]                  // and the cell hasn't been visited
        ) {
            // Mark the cell as visited
            visited[row][col] = true; 
            
            // If the cell contains land, continue visiting adjacent cells
            if (grid[row][col] == '1') {
                visitLandDepthFirst(row - 1, col, grid, visited); // North
                visitLandDepthFirst(row + 1, col, grid, visited); // South
                visitLandDepthFirst(row, col - 1, grid, visited); // West
                visitLandDepthFirst(row, col + 1, grid, visited); // East
            }
        }
    }
}