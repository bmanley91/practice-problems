class Solution {
    // Brute Force O(n^2) - Not ideal
    public int trap(int[] height) {
        int output = 0;
        
        // We don't look at the edges since they can't hold water.
        for (int i = 1; i < height.length - 1; i++) {
            // Find the highest point to the left
            int leftMax = 0;
            for (int left = i; left >= 0; left--) {
                leftMax = Math.max(leftMax, height[left]);
            }
            
            // Find the highest point to the right
            int rightMax = 0;
            for (int right = i; right < height.length; right++) {
                rightMax = Math.max(rightMax, height[right]);
            }
            
            // Add the difference between the current point 
            // and the _min_ of the left and right maxes to the output
            output += Math.min(leftMax, rightMax) - height[i];
        }
        
        return output;
    }
}