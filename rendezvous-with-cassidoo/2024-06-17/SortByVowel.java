class SortByVowel {
    public String[] sort(String[] names) {
        // Iterate through each string and put them into a 
        /// Map with their vowel counts as the key
        // Map value is list of strings since multiple names can have the
        /// same vowel count
        // Could probably do this with some wild custom comparator

        Map<Integer, List<String>> vowelCountMap = new HashMap();

        int highestCount = 0;
        for (String name : names) {
            // Count vowels
            int vowelCount = 0;
            for (char c : name.toCharArray()) {
                char cLower = Character.toLowerCase(c);
                if (cLower == 'a' || cLower == 'e' || cLower == 'i' || cLower == 'o' || cLower == 'u') {
                    vowelCount++;
                }
            }
            highestCount = Integer.max(highestCount, vowelCount);

            if (vowelCountMap.containsKey(vowelCount)) {
                vowelCountMap.get(vowelCount).add(name);
            } else {
                List<String> nameList = new ArrayList();
                nameList.add(name);
                vowelCountMap.put(vowelCount, nameList);
            }
        }

        String[] output = new String[names.length];
        int outputIndex = 0;
        int currCount = highestCount;
        while(0 <= currCount) {
            if (vowelCountMap.containsKey(currCount)) {
                // Get the names back out of the map
                List<String> namesWithCount = vowelCountMap.get(currCount);

                // Sort names
                Collections.sort(namesWithCount);

                // Add each to the output
                for (String name : namesWithCount) {
                    output[outputIndex] = name;
                    outputIndex++;
                }
            }
            currCount--;
        }
        return output;
    }
}
