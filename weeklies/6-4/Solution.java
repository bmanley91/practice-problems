import java.util.Arrays;

class Solution {
    public int numberOfSpecialSubstrings(String s) {
        
        // Sliding window
        // Keep track of the last index of each char seen
        // While the char at right has not been seen in the window
        /// keep advancing right and increment unique special string counter
        // Once right hits a char which has been seen in the window,
        /// move left to the last position of the detected character + 1.

        // Edge case considerations
        // All of the chars in s are lowercase letters

        int left = 0;
        int right = 0;
        int counter = 0;
        int[] lastCharIndex = new int[26];
        Arrays.fill(lastCharIndex, -1);
        
        while (left < s.length() && right < s.length()) {
            System.out.println("Window is " + left + " to " + right);
            char currentChar = s.charAt(right);

            // If the right char is not in the window,
            // Then we have a new special string
            // Record the latest index for this char
            // Expand the window to the right
            if (lastCharIndex[currentChar - 'a'] < left) {
                System.out.println("Char " + currentChar + " is unique in window");
                counter += right - left + 1;
                lastCharIndex[currentChar - 'a'] = right;
                right++;
            } 
            // Otherwise, we do not have a special string
            // Find the last index of the current char
            /// increment left to that + 1
            // Set the last occurrance of the current char to right
            else {
                System.out.println("Char " + currentChar + " is not unique in window");
                left = lastCharIndex[currentChar - 'a'] + 1;
                lastCharIndex[currentChar - 'a'] = right-1;
            }
            System.out.println("Count is now " + counter + "\n");
        }
        
        System.out.println("Count after loop " + counter + " left pointer at " + left);

        return counter;
    }
}
