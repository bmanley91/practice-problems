
import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int minimumPushes(String word) {
        // Count the number of times that each char occurs
        // Sort by count descending
        // Fill the buttons in order of occurrance count
        // Since we have 2 to 9 that we can assign, we have 8 buckets
        // Fill buckets' "one press" slot first, then "two press", etc

        // For actual data structures, we'll need a map to count chars
        // We can use a sorted/tree map to maintain order by count
        // We shouldn't actually need to model the buckets/buttons
        // Once we've iterated through 8 chars, the cost increases by 1
        
        // Build the char occurrance map
        // (Optimization - This could be replaced with an array I guess)
        // (According to some guy on leetcode sorting an array of 26 elements is O(1)...?)
        Integer[] charOccurrances = new Integer[26];
        Arrays.fill(charOccurrances, 0);
        for (int i = 0; i < word.length(); i++) {
            charOccurrances[word.charAt(i) - 'a']++;
        }
        
        Arrays.sort(charOccurrances, Collections.reverseOrder());
        
        int totalPresses = 0;
        for (int i = 0; i < charOccurrances.length; i++) {
            // Short circuit once we've reached chars that don't appear
            if (charOccurrances[i] == 0) break;

            // Keep track of cost
            int cost = (i / 8) + 1; 
            totalPresses += charOccurrances[i] * cost;
        }
        return totalPresses;
    }
}