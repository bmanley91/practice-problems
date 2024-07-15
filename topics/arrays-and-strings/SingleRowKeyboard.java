class Solution {
    // Iterate through the keyboard string, char by char
    // Put the char and its index into a HashMap so that we can quickly 
    // lookup their locations later.
    // Check the difference between the value in the map for the current char 
    // vs that of the previous char to look up time between chars
    // Sum up time between chars
    public int calculateTime(String keyboard, String word) {
        // Base Case - If the word is empty, it takes no time to type.
        if (word.length() == 0) return 0;
        
        // Put the char and its index into a HashMap so that we can quickly 
        // lookup their locations later.
        Map<Character, Integer> charIndexMap = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            // From the problem, we know that each char only appears once
            // So we don't need to check if a char is already in the map.
            charIndexMap.put(keyboard.charAt(i), i);
        }
        
        // Finger starts on the first char
        char startingChar = keyboard.charAt(0);
        
        int totalTime = 0;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            
            // Check the difference between the value in the map for the current char 
            // vs that of the previous char to look up time between chars
            // Sum up time between chars
            int startingIndex = charIndexMap.get(startingChar);
            int currentIndex = charIndexMap.get(currentChar);
            totalTime += Math.abs(currentIndex - startingIndex);
            
            startingChar = currentChar;
        }
        
        return totalTime;
    }
}