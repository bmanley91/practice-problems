class ReverseString {
    public void reverseString(char[] s) {
        // Use two pointers
        // One starts at 0, the other at s.length - 1
        int low = 0;
        int high = s.length - 1;
        
        // Swap the chars at the low and high pointers
        // Increment low and decrement high
        // Stop looping when low > high
        while (low < high) {
            char temp = s[low];
            s[low] = s[high];
            s[high] = temp;
            
            low++;
            high--;
        }
    }
}