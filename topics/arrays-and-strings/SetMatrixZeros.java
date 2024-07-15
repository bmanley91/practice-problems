class Solution {
    // Iterate through the matrix
    // Use two set to store the columns and rows with zeroes
    // When we reach a zero, put the current col and row into their sets
    // Then set everything above the zero to 0, ditto for everything to the left of it
    // Once we evaluate the current cell, check the col and row sets to see if we 
    // need to set it to 0.
    public void setZeroes(int[][] matrix) {
        Set<Integer> rowZeroSet = new HashSet<Integer>();
        Set<Integer> colZeroSet = new HashSet<Integer>();
        
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                // Evaluate the current cell
                if (matrix[row][col] == 0) {
                    rowZeroSet.add(row);
                    colZeroSet.add(col);
                    setLeftToZero(matrix, row, col);
                    setRightToZero(matrix, row, col);
                }
                
                // See if we need to set the current cell to zero before we move on
                if (
                    rowZeroSet.contains(row) ||
                    colZeroSet.contains(col)
                ) {
                    matrix[row][col] = 0;
                }
            }
        }
    }
    
    private void setLeftToZero(int[][] matrix, int row, int col) {
        for (int c = 0; c < col; c++) {
            matrix[row][c] = 0;
        }
    }
    
    private void setRightToZero(int[][] matrix, int row, int col) {
        for (int r = 0; r < row; r++) {
            matrix[r][col] = 0;
        }
    }
}