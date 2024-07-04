class Solution {
    public boolean judgeSquareSum(int c) {
        // Fairly straightforward solution using Math.sqrt
        // and two pointers

        // Use longs in case we have to deal with a large number
        // Pointers start at 0 and square root of c
        /// 0 because 2^2 + 0^2 == 4 is a valid case
        /// sqrt(c) because anything higher than sqrt(c) + 0^2 would be higher than c.
        long low = 0;
        long high = (long)Math.sqrt(c);

        while (low <= high) {
            long result = (low * low) + (high * high);
            if (result < c) {
                // Too low, make the result bigger
                low++;
            } else if (result > c) {
                // Too high, make the result smaller
                high--;
            } else {
                // Just right - return!
                return true;
            }
        }
        // If we finish our search in the loop above, then we must have not found a valid answer
        return false;
    }
}
