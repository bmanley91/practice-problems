class Solution {
    // Iterate through the list, finding the longest gap between two filled seats.
    // Use a running count which will ++ when we're on a 0
    // and will reset to zero when we hit a 1
    // At each zero we want to find the midpoint of the current zeroes group.
    // The above approach works for all middle seats. We need special logic for the edges
    
    // For each edge, count the 0s moving inward until we reach a 1.
    // Compare the distance of the first 1 that we find to the relevant end
    // If that distance is longer than our longest so far, replace the longest so far.
    public int maxDistToClosest(int[] seats) {
        int longestDistance = 0;
        int runningCount = 0;
        
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                runningCount = 0;
            } else {
                runningCount++;
                // Find the midpoint of the current group of 0s
                int currentDistance = (runningCount + 1) / 2;
                longestLength = Math.max(longestLength, currentDistance);
            }
        }
        
        // Check left edge
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                longestLength = Math.max(longestLength, i);
                break; // Stop looping since we found the first 1 on the left.
            }
        }
        
        // Check right edge
        for (int i = seats.length - 1; i >= 0; i--) {
            if (seats[i] == 1) {
                longestLength = Math.max(longestLength, seats.length - 1 - i);
                break; // Stop looping since we found the first 1 on the right.
            }
        }
        
        return longestLength;
    }
}