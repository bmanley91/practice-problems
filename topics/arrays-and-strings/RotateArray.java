class Solution {
    // This solution involves creative array rotation.
    // Given an array [0, 1, 2, 3, 4] and k=3, we expect to get [2, 3, 4, 0, 1]
    // We'll reverse the full array => [4, 3, 2, 1, 0]
    // Then we'll reverse the first k elements => [2, 3, 4, 1, 0]
    // Then we'll reverse the remaining elements => [2, 3, 4, 0, 1]
    public void rotate(int[] nums, int k) {
        // If k is greater than nums.length, then we could wind up doing a bunch of extra work.
        // If we have an array with three elements [0, 1, 2]
        // then rotating 5 times is the same as rotating 2 times (5 % 3 = 2).
        // We could subtract, but that could get repetitive if k is multiple times the length of nums,
        // so we use modulo.
        k = k % nums.length;
        
        // Reverse the whole array.
        reverseInRange(nums, 0, nums.length - 1);
        
        // Reverse the first k elements (indexes from 0 to k - 1)
        reverseInRange(nums, 0, k - 1);
        
        // Reverse the remaining upper elements (indexes from k to nums.length - 1)
        reverseInRange(nums, k, nums.length - 1);
    }
    
    // Helper method to reverse the elements in an array between two indicies.
    private void reverseInRange(int[] nums, int low, int high) {
        // Treat high and low as two pointers
        // Swap the elements they're pointing at 
        // Then decrement high and increment low
        while (low < high) {
            int temp = nums[low];
            nums[low] = nums[high];
            nums[high] = temp;
            low++;
            high--;
        }
    }
}