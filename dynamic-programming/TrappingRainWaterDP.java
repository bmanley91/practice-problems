class Solution {
    // Dynamic Programming approach - O(n) - Three passes
    public int trap(int[] height) {
        // Base Case - If there's nothing in the height array we can't collect water
        if (height.length == 0) return 0;
        
        // Instead of scanning through all other elements we can do 
        // 2 scans to find the left and right maxes at each given point.
        // Then we can use these stored maxes to compute the water that 
        // will accumulate at a given point.
        
        // Store maxes from left to right in leftMaxes
        int[] leftMaxes = new int[height.length];
        leftMaxes[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            // The leftMax at a given point is either the current height
            // or the max one space to the left.
            leftMaxes[i] = Math.max(height[i], leftMaxes[i - 1]);
        }
        
        // Store maxes from right to left in rightMaxes
        int[] rightMaxes = new int[height.length];
        rightMaxes[height.length - 1] = height[height.length - 1];
        for(int i = height.length - 2; i >= 0; i--) {
            // The rightMax at a given point is either the current height
            // or the max one space to the right.
            rightMaxes[i] = Math.max(height[i], rightMaxes[i + 1]);
        }
        
        // We now have our left and right maxes stored
        // Do one more pass using the stored maxes to compute the water depth at each point
        int output = 0;
        for (int i = 0; i < height.length; i++) {
            output += Math.min(leftMaxes[i], rightMaxes[i]) - height[i];
        }
        
        return output;
    }
}