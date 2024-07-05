class Solution {
    public int longestSubarray(int[] nums, int limit) {
        // Keep track of highest and lowest values in a sliding window
        // Keep iterating right index until abs(window.max - window.min) > limit
        /// Then iterate left index until abs(window.max - window.min) <= limit
        /// If left index reaches right index, increment right
        // We're done once right reaches the end of the array
        // Keep track of max valid window size as we go
        
        // Edge case considerations
        // limit can be 0
        
        // [8,5,6,2,4,7], limit 4
        // 0: min: 8, max: 8, diff: 0
        // Increment right
        // 1: min: 5, max: 8, diff: 3
        // Increment right
        // 2: min: 5, max: 8, diff: 3
        // Increment right
        // 3: min: 2, max: 8, diff: 5
        // Increment left 
        // 4: min: 2, max: 6, diff: 4
        // Increment right

        // Use something for mins and maxes? Heaps, priority queues?
        // Double queue will work!

        Deque<Integer> increase = new LinkedList<Integer>();
        Deque<Integer> decrease = new LinkedList<Integer>();

        int maxLength = 0;
        int left = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            // Adjust the min/max queues for the current number
            while(increase.size() > 0 && num < increase.getLast()) {
                increase.removeLast();
            }
            increase.add(num);

            while (decrease.size() > 0 && num > decrease.getLast()) {
                decrease.removeLast();
            }
            decrease.add(num);

            while(Math.abs(decrease.getFirst() - increase.getFirst()) > limit) {
                if (nums[left] == decrease.getFirst()) {
                    decrease.removeFirst();
                }

                if (nums[left] == increase.getFirst()) {
                    increase.removeFirst();
                }
                left++;
            }
            int currentSize = i - left + 1;
            maxLength = Math.max(maxLength, currentSize);
        }
        return maxLength;
    }
}
