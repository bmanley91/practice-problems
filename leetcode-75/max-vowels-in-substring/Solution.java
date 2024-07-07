class Solution {
    public int maxVowels(String s, int k) {
        // Use a queue to model the window
        // Iterate through the first k chars of the string
        // Increment vowel count if we see a vowel
        /// String is only lowercase
        // Put each char into the queue
        // Once we reach a window of size k
        /// Poll front of queue
        /// If the character polled from queue is a vowel
        //// Decrement current count
        /// Check current char in string
        /// If it's a vowel, increment count
        /// Either way, enqueue the char

        Queue<Character> window = new LinkedList<>();

        int vowelCount = 0;
        int curr = 0;
        while (curr < k) {
            if (isVowel(s.charAt(curr))) {
                vowelCount++;
            }
            window.add(s.charAt(curr));
            curr++;
        }
        int max = vowelCount;

        // We now have a window of size k
        while (curr < s.length()) {
            // Remove oldest char from window
            char removed = window.poll();
            if (isVowel(removed)) {
                vowelCount--;
            }
            
            // Add new char to window
            window.add(s.charAt(curr));
            if (isVowel(s.charAt(curr))) {
                vowelCount++;
            }
            max = Integer.max(
                max,
                vowelCount
            );
            curr++;
        }
        return max;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
