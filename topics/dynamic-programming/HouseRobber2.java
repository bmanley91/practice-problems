class HouseRobber2 {
    public int rob(int[] nums) {
        // Base Case - Single house
        if (nums.length == 1) {
            return nums[0];
        }
        
        // Follow the same DP process that's used in the regular, linear Robber Problem
        // Since nums[0] and nums[last] are neighbors, do the calculation twice
        // Once for nums[0] to nums[last - 1] and once for nums[1] to nums[last]. 
        // Then return the higher of the two results
        return Math.max(
            findMaxForRange(nums, 0, nums.length - 2),
            findMaxForRange(nums, 1, nums.length - 1)
        );
    }
    
    // Helper function to find the max we can get from a range of houses so that 
    // we don't need to duplicate code.
    // start must be > 0
    // end must be < nums.length - 1
    private int findMaxForRange(int[] nums, int start, int end) {
        int previousMax = 0;
        int currentMax = 0;
        for (int i = start; i <= end; i++) {
            int temp = currentMax;
            currentMax = Math.max(previousMax + nums[i], currentMax);
            previousMax = temp;
        }
        
        return currentMax;
    }
}