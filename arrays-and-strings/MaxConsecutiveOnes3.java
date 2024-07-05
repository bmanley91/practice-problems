class Solution {
    public int longestOnes(int[] nums, int k) {
        
        // Sliding window
        // Left starts at beginning
        // Right iterates
        // If right hits a 0, store the index in a queue
        // If queue size > k,
        /// Poll queue and move start to the index returned + 1
        // On each iteration, check if the current length is higher than the running max

        // Edge case considerations
        // k will be at most nums.length
        // k can be 0
        // nums will not be empty
        
        int left = 0;
        int right = 0;
        int maxLength = 0;
        Queue<Integer> zeroIndexes = new LinkedList<Integer>();

        while (right < nums.length) {
            // If right is pointing at a 0
            // Enqueue the right index
            if (nums[right] == 0) {
                zeroIndexes.add(right);

                // Then check if the queue is too big
                // If it is, then move start to the index at the front of the queue plus 1
                if (zeroIndexes.size() > k) {
                    int leftMostZeroIndex = zeroIndexes.poll();
                    left = leftMostZeroIndex + 1;
                }
            }
            
            
            // Length at any given point is right - left + 1
            // left 0, right 0 -> length 1
            // left 3, right 10 -> length 8
            int currentLength = right - left + 1;
            maxLength = Integer.max(currentLength, maxLength);

            // Always iterate right pointer at the end of the loop
            right++;
        }
        return maxLength;
    }
}