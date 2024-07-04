class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        // Divide into sub problems
        // Count all of the odd numbers in the whole array
        // Check sub array to the left by removing the right most element
        // Check the sub array to the right by removing the left most element
        // Check by looking at the number being removed and seeing if it's odd
        // If it is decrement count
        // If count drops below k, then don't continue down this side anymore
        
        int oddNumberCount = 0;
        for (int num : nums) {
            if (num % 2 == 1) {
                oddNumberCount++;
            }
        }

        return checkSubArray(nums, k, oddNumberCount, 0, nums.length - 1);
    }

    int checkSubArray(int[] nums, int k, int oddCount, int left, int right) {
        System.out.println("There are " + oddCount + " odds between " + left + " and " + right);
        if (oddCount < k) {
            return 0;
        }

        // Check current
        int current = oddCount == k ?
            1 :
            0;

        // Remove right
        int rightCount = isOdd(nums[right]) ?
            oddCount - 1 :
            oddCount;

        // Remove left
        int leftCount = isOdd(nums[left]) ?
            oddCount - 1 :
            oddCount;
        
        return current +
            checkSubArray(nums, k, rightCount, left, right-1) +
            checkSubArray(nums, k, leftCount, left+1, right);
    }

    private boolean isOdd(int num) {
        return num % 2 == 1;
    }
}
