class Solution {
    // Create a boolean array "canReachEnd" which represents if we can 
    // reach the last index from the current index.
    // We work backwards through nums and check the n values in canReachEnd to the 
    // right of the current index (i), if any of them are true, then canReachEnd[i] = true
    // At the end, return canReachEnd[0]
    public boolean canJump(int[] nums) {
        boolean[] canReachEnd = new boolean[nums.length];
        // You can reach the last index from the last index.
        canReachEnd[nums.length - 1] = true;
        
        for (int i = nums.length - 2; i >= 0; i--) {
            canReachEnd[i] = checkRightOfIndex(nums, canReachEnd, i);
        }
        
        return canReachEnd[0];
    }
    
    private boolean checkRightOfIndex(int[] nums, boolean[] canReachEnd, int targetIndex) {
        int rightPointer = targetIndex + 1;
        // While we're in bounds, and in range of the current step
        while (
            rightPointer < nums.length &&
            rightPointer <= nums[targetIndex] + targetIndex
        ) {
            if (canReachEnd[rightPointer]) {
                return true;
            }
            rightPointer++;
        }
        return false;
    }
}