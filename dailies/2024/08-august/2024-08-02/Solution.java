class Solution {
    public int minSwaps(int[] nums) {
        // Count the number of 1s in the array `count`
        // Create a sliding window of size `count`
        // Iterate through the array
        /// count the number of zeros in the window with each element as a starting point
        /// once we get to the point where the end of the window is past the end of the array
        //// then use numbers from the start of the array (index = index - nums.length)
        // The min number of zeros in the sliding window is the minimum number of swaps required

        // Count all 1s to figure out the window size
        int windowSize = countOnes(nums);

        // Short circuit if the array is all ones or zeros
        if (nums.length == windowSize || windowSize == 0) {
            return 0;
        }

        // Create the initial window
        int zerosInWindow = 0;
        Queue<Integer> window = new LinkedList<Integer>();
        for (int i = 0; i < windowSize; i++) {
            window.add(nums[i]);
            if (nums[i] == 0) {
                zerosInWindow++;
            }
        }

        // Process the rest of the array
        int minZeros = zerosInWindow;
        for (int i = windowSize; i < nums.length; i++) {
            // Remove the first element in the window and adjust zero count accordingly
            int removed = window.poll();
            if (removed == 0) zerosInWindow--;

            // Add the next elelemtn and adjust zero count accordingly
            window.add(nums[i]);
            if (nums[i] == 0) zerosInWindow++;

            // Keep track of lowest zero count
            minZeros = Integer.min(minZeros, zerosInWindow);
        }

        // Once we've processed the whole array, we need to process the wraparound cases
        // This basically means following the same process that we just did for the first "windowSize" elements
        for (int i = 0; i < windowSize; i++) {
            // Remove the first element in the window and adjust zero count accordingly
            int removed = window.poll();
            if (removed == 0) zerosInWindow--;

            // Add the next elelemtn and adjust zero count accordingly
            window.add(nums[i]);
            if (nums[i] == 0) zerosInWindow++;

            // Keep track of lowest zero count
            minZeros = Integer.min(minZeros, zerosInWindow);
        }

        return minZeros;
    }

    private int countOnes(int[] nums) {
        int count = 0;
        for (int num : nums) {
            count += num;
        }
        return count;
    }
}
