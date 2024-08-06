import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Character, Integer> charOccurrances = new HashMap<Character, Integer>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            charOccurrances.put(
                c,
                charOccurrances.getOrDefault(c, 0) + 1
            );
        }

        // Sort the keys by occurrance count descending
        List<Map.Entry<Character, Integer>> orderedPairs = charOccurrances
            .entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .toList();
        
        int totalPresses = 0;
        for (int i = 0; i < orderedPairs.size(); i++) {
            // Keep track of cost
            int cost = (i / 8) + 1; 
            totalPresses += orderedPairs.get(i).getValue() * cost;
        }
        return totalPresses;
    }
}