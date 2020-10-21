class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        // Put banned words into a set for faster lookup time later
        Set<String> bannedWordsSet = new HashSet<String>(Arrays.asList(banned));
        bannedWordsSet.add(""); // Added for edge case caused by splitting on punctuation.
        
        // Since we don't care about case, make all letters lowercase
        String lowercaseParagraph = paragraph.toLowerCase();
        
        // Split the paragraph into individual words
        // This regex can probably be improved.
        // If you find yourself writing regex in an interview maybe reevaluate if you want this job.
        String[] paragraphArray = lowercaseParagraph.split("[\\!\\?\\'\\,\\;\\.\\s+]"); 
        
        // Create a HashMap to store words and their counts.
        // Iterate through word array counting the occurrences of words as we go.
        // Keep track of the most frequent word as we go.
        // Make sure to ignore banned words.
        Map<String, Integer> wordCountMap = new HashMap<>();
        String mostFrequentWord = null;
        
        for (String word : paragraphArray) {
            if (!bannedWordsSet.contains(word)) {
                // If we don't have a most frequent word yet, then set it
                if (mostFrequentWord == null) {
                    mostFrequentWord = word;
                }
                
                // Figure out our new word count and put it into the map.
                int updatedWordCount = 1;
                if (wordCountMap.get(word) != null) {
                    updatedWordCount += wordCountMap.get(word);
                }
                wordCountMap.put(word, updatedWordCount);
                
                // Update the most frequent word if we have to
                if (wordCountMap.get(mostFrequentWord) < updatedWordCount) {
                    mostFrequentWord = word;
                }
            }
        }
        
        return mostFrequentWord;
    }
}