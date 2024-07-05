class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        // lol this looks familiar
        // Iterate through once to get base satisfaction
        // Iterate through again using a sliding window of size `minutes`
        /// to figure out the diff if that specific window was updated.
        /// Apply the diff to the base satisfaction.
        // Repeat for all possible windows and return the max satisfaction

        // Edge case considerations
        /// customers and grumpy are always the same length
        /// minutes can be any value from 1 to customers.length

        // Get Base satisfaction
        int baseSatisfaction = 0;
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                baseSatisfaction += customers[i];
            }
        }

        // Iterate through with window to find diffs
        int start = 0;
        int end = start + minutes;
        int maxSatisfaction = baseSatisfaction;
        while (end <= customers.length) {
            int diff = 0;
            // Window includes start, excludes end
            for(int i = start; i < end; i++) {
                // We only care about the case where the owner goes from grumpy to not grumpy
                // If the owner goes from 0 to 0 nothing actually changes.
                if (grumpy[i] == 1) {
                    diff += customers[i];
                }
            }

            maxSatisfaction = Integer.max(
                maxSatisfaction, 
                baseSatisfaction + diff
            );

            start++;
            end++;
        }

        return maxSatisfaction;
    }
}
