class Solution { 
    // Two passes: 
    // First pass counts characters and puts them into a map of char to count.
    // Second pass checks each characters count in the map. 
    // Once we find a char with a count of 1, return the current index.
    // If we finish the second loop and don't find any chars with a count of 1, return -1.
    public int firstUniqChar(String s) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        
        // First pass counts characters and puts them into a map of char to count.
        for (int i = 0; i < s.length(); i++) {
            if (charCountMap.get(s.charAt(i)) != null) {
                charCountMap.put(s.charAt(i), charCountMap.get(s.charAt(i)) + 1);
            } else {
                charCountMap.put(s.charAt(i), 1);
            }
        }
        
        // Second pass checks each characters count in the map. 
        for (int i = 0; i < s.length(); i++) {
            if (charCountMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        
        return -1;
    }
}