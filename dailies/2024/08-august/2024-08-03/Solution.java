class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        // Create a frequency "map" array
        // Elements can go up to 1000 so make array size 1001
        // Keep track of the highest number that we see so that we can keep our loop at the end smaller
        
        // Iterate through both arrays at the same time
        /// Increment the frequency at target[i]
        /// Decrement the frequency at arr[i]
        
        // Loop through the frequency map from 0 to max
        /// return false if any frequencies do not equal 0
        // Return true if we finish the loop

        int[] freq = new int[1001];
        int min = target[0];
        int max = target[0];
        for (int i = 0; i < target.length; i++) {
            freq[arr[i]]--;
            freq[target[i]]++;

            // Some optimization that might not be worth it lol
            max = Integer.max(max, arr[i]);
            max = Integer.max(max, target[i]);
            min = Integer.min(min, arr[i]);
            min = Integer.min(min, target[i]);
        }

        for (int i = min; i <= max; i++) {
            if (freq[i] != 0) return false;
        }
        return true;
    }
}
