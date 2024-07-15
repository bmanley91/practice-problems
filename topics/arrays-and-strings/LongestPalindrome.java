class Solution {
    // Iterate through each char
    // Find the longest palindrome that can be created around that char.
    // // Use two pointers to expand around the char until we hit either end of the string
    // // or until the chars at each pointer aren't the same.
    // We need to do this for two cases
    // 1. Both pointers start on the current char
    // 2. One pointer starts on the current char, one pointer starts on the char after the current char.
    public String longestPalindrome(String s) {
        String longestPalindrome = "";
        for (int i = 0; i < s.length(); i++) {
            String oddString = findLongestPalindromeAroundIndexes(s, i, i, "");
            String evenString = findLongestPalindromeAroundIndexes(s, i, i + 1, "");
            
            String longerCurrentString = oddString.length() > evenString.length() ?
                oddString :
                evenString;
            
            if (longerCurrentString.length() > longestPalindrome.length()) {
                longestPalindrome = longerCurrentString;
            }
        }
        return longestPalindrome;
    }
    
    // Helper function to find the longest palindrome in a string given two indexes.
    private String findLongestPalindromeAroundIndexes(String input, int left, 
                                                      int right, String palindrome) {
        // First check to make sure neither input is out of bounds.
        // If so return our running palindrome.
        if (left < 0 || right >= input.length()) return palindrome;
        
        // If the current chars match, recursively check the next chars
        if (input.charAt(left) == input.charAt(right)) {
            return findLongestPalindromeAroundIndexes(
                input, left - 1, right + 1, input.substring(left, right + 1));
        }
        
        // If the current chars do not match, return our running palindrome.
        return palindrome;
    }
}