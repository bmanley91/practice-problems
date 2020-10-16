// https://leetcode.com/problems/max-area-of-island/
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Scan through each cell
        // Keep track of cells that we've visited so that we don't double count.
        // When we encounter a 1, do a BFS around the cell to count the number of connected 1s. 
        // When we count a conneted 1, mark it as visited so we don't check it again.
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        int maxSize = 0;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // We only want to start counting if the current cell is an unvisited 1.
                if (grid[row][col] == 1 && !visited[row][col]) {
                    // Do BFS around the current cell
                    int currentCount = bfs(grid, row, col, visited);
                    maxSize = Math.max(maxSize, currentCount);
                }
                
                visited[row][col] = true;
            }
        }
        
        return maxSize;
    }
    
    private int bfs(int[][] grid, int row, int col, boolean[][] visited) {
        // We only start searching on 1s so start with a count of 1.
        int count = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {row, col});
        visited[row][col] = true;
        
        while (!queue.isEmpty()) {
            int[] currentCell = queue.poll();
            int currentRow = currentCell[0];
            int currentCol = currentCell[1];
            
            if (currentRow >= 0 && currentCol >= 0 && currentRow < grid.length && currentCol < grid[0].length) {
                // Check North iff it's in bounds
                if (currentRow - 1 >= 0) {
                    if (
                        grid[currentRow - 1][currentCol] == 1 &&  // If we're looking at a 1
                        !visited[currentRow - 1][currentCol]      // And we haven't visited the target cell.
                    ) {
                        // Then increment our count and add the North cell to the queue
                        visited[currentRow - 1][currentCol] = true;
                        queue.add(new int[] {currentRow - 1, currentCol});
                        count++;
                    }
                }

                // Check South iff it's in bounds
                if (currentRow + 1 < grid.length) {
                            // If we're not out of bounds
                    if (grid[currentRow + 1][currentCol] == 1 &&  // If we're looking at a 1
                        !visited[currentRow + 1][currentCol]      // And we haven't visited the target cell.
                    ) {
                        // Then increment our count and add the South cell to the queue
                        visited[currentRow + 1][currentCol] = true;
                        queue.add(new int[] {currentRow + 1, currentCol});
                        count++;
                    }
                }

                // Check West iff it's in bounds
                if (currentCol - 1 >= 0) {         
                    if(grid[currentRow][currentCol - 1] == 1 &&  // If we're looking at a 1
                        !visited[currentRow][currentCol - 1]     // And we haven't visited the target cell.
                    ) {
                        // Then increment our count and add the South cell to the queue
                        visited[currentRow][currentCol - 1] = true;
                        queue.add(new int[] {currentRow, currentCol + 1});
                        count++;
                    }
                }

                // Check East iff it's in bounds
                if (currentCol + 1 < grid[0].length) {
                    if (grid[currentRow][currentCol + 1] == 1 &&  // And we're looking at a 1
                        !visited[currentRow][currentCol + 1]      // And we haven't visited the target cell.
                    ) {
                        // Then increment our count and add the South cell to the queue
                        int newCol = currentCol + 1;
                        System.out.println("Adding {" + currentRow + ", " + newCol + "}");
                        System.out.println(grid[0].length);
                        visited[currentRow][currentCol + 1] = true;
                        queue.add(new int[] {currentRow, newCol});
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
}