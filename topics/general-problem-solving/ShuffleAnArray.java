class Solution {
    
    private int[] original;
    private int[] current;
    
    Random random = new Random();

    public Solution(int[] nums) {
        // Record the original and a working copy, "current," of the input 
        // "current" will be the array that we shuffle.
        // "original" will be used to reset "current" when reset() is called.
        // We use the array clone() to make a copy of the input, 
        // rather than having both variables point to the same array.
        this.original = nums;
        this.current = nums.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        // Very similar logic to what we have in the constructor. 
        current = original;
        original = original.clone();
        return original;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        // Loop through the array
        // Look for a random index between the current index and the end of the list
        // Swap the elements at the current and other random index
        for (int i = 0; i < current.length; i++) {
            swap(i, getRandomIndex(i, current.length));
        }
        return current;
    }
    
    // Helper method to generate a random index within a range.
    private int getRandomIndex(int min, int max) {
        // Random.nextInt generates an int number under the given number
        // So we generate a random number under the difference between max and min then add it to max.
        // This results in a number that is between max and min.
        return random.nextInt(max - min) + min;
    }
    
    // Helper method to swap two elements in the current array.
    private void swap(int index1, int index2) {
        int temp = current[index1];
        current[index1] = current[index2];
        current[index2] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */