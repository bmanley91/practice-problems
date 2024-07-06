class Solution {
    public int passThePillow(int n, int time) {
        // This one is just math
        // Since we start at one end of the number line, a trip takes n-1 seconds
        // Figure out direction with (int) time / n-1
        /// If the quotient is odd, then the pillow is headed backwards
        /// If the quotient is even, then the pillow is headed forward
        // Figure out position with time % n-1 = position
        /// The result is the offset from the end
        /// So if we're moving forward, the answer is position
        /// If we're moving backward, the answer is n - position
        
        int tripNumber = time / (n-1);
        int positon = time % (n-1);

        if (tripNumber % 2 == 0) {
            // Forward Case
            return 1 + positon;
        } else {
            // Backwards Case
            return n - positon;
        }
    }
}
