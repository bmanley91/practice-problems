class Solution {
    // Create a model of the champagne tower
    // Each row will consist of "row index" number of glasses:
    // row 1 has 1 glass [[0,0]]
    // row 2 has 2 glasses [[1, 0], [1, 1]]
    // row 3 has 3 glasses, etc [[2, 0], [2, 1], [2, 2]]
    // We simulate pouring the champagne by looking at each individual 
    // glass and seeing how much champagne reached it.
    // Anything over 1.0 will go to the next row.
    // A glass at position g in row r
    // will pour half of the remainder into glass g and glass g + 1 in row r + 1
    public double champagneTower(int poured, int queryRow, int queryGlass) {
        // Create a model of the champagne tower
        // The size of each dimension needs to be 102 since the problem starts at index 0 (add 1)
        // and because anything that spills out of the bottom row gets poured on the floor (add 1 row for the "floor").
        double[][] tower = new double[102][102];
        tower[0][0] = (double) poured;
        
        // We only need to simulate up to the query row
        for (int row = 0; row <= queryRow; row++) {
            // We also only need to simulate up to the current row, since that's the highest glass index we'll have in a given row.
            for (int glassIndex = 0; glassIndex <= row; glassIndex++) {
                // If there's more than 1.0 in the current glass, spill it into the next glasses
                if (tower[row][glassIndex] > 1.0) {
                    double amountSpilled = tower[row][glassIndex] - 1.0;
                    
                    // Half of the spilled amount gets added to each glass under the current one
                    tower[row + 1][glassIndex] += amountSpilled / 2;
                    tower[row + 1][glassIndex + 1] += amountSpilled / 2;
                }
            }
        }
        
        return tower[queryRow][queryGlass] > 1.0 ? 1.0 : tower[queryRow][queryGlass];
    }
}