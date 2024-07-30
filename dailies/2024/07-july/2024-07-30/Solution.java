class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int[] aRight = new int[n];
        int[] bLeft = new int[n];

        // Start counts at each end at 0
        aRight[n - 1] = s.charAt(n - 1) == 'a' ? 1 : 0;
        bLeft[0] = s.charAt(0) == 'b' ? 1 : 0;

        // Start iteration at 1 since we filled the base case in the counts
        // Use a while loop so that we don't have to specifically
        /// handle the s.length() == 1 edge case
        int index = 1;
        while (index < n) {
            // If the char at the current index is a b,
            // add to the prefix counter.
            bLeft[index] = s.charAt(index) == 'b' ?
                bLeft[index - 1] + 1 :
                bLeft[index - 1];

            // If the char at right index is an a,
            // add to the a suffix counter
            int rightIndex = n - index - 1;
            aRight[rightIndex] = s.charAt(rightIndex) == 'a' ?
                aRight[rightIndex + 1] + 1 :
                aRight[rightIndex + 1];

            index++;
        }

        // Final pass through the string
        // Use the current char as a pivot
        // See what the result would be if we deleted all of the b's to the left
        // and all of the a's to the right
        /// Subtract 1 from this total because we wouldn't delete the current char.
        int deleteCounter = n;
        for (int i = 0; i < n; i++) {
            deleteCounter = Integer.min(
                deleteCounter,
                bLeft[i] + aRight[i] - 1
            );
        }
        return deleteCounter;
    }
}