class Solution {
    public int numberOfSpecialSubstrings(String s) {
        int[] count = new int[26];
        int left = 0;
        int counter = 0;
        
        // Keep extending the right side of the window
        for (int right = 0; right < s.length(); right++) {
            // Increment count for the current character
            count[s.charAt(right) - 'a']++;

            // Keep checking window from left to right until we find a string with no duplicate chars
            // We know that each substring within this range must also contain no duplicates
            while (!check(count)) {
                count[s.charAt(left) - 'a']--;
                left++;
            }
            counter += right - left + 1;
        }
        return counter;
    }

    // Utility to check if there are any chars with more than one occurrance in the string.
    public boolean check(int[] count) {
        for (int c : count) 
			if (c > 1) 
				return false;
        return true;
    }
}
