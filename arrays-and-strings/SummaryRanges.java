class Solution {
    // Use two pointers to move through the list
    // One pointer will keep track of the start of the range
    // The other will move forward through the list to find the end of the range.
    // A range ends when there's a gap in the numbers
    // i.e. nums[i] != nums[i - 1] + 1
    // Once a range ends, construct a range string and add it to the output list
    public List<String> summaryRanges(int[] nums) {
        List<String> outputList = new ArrayList<>();
        
        // Base Case - If the input is empty, short circuit.
        if (nums.length == 0) return outputList;
        
        int low = 0;
        int high = 1;
        
        while (high < nums.length) {
            // If this happens, that means the current range ended at the previous high index.
            if (nums[high] != nums[high - 1] + 1) {
                outputList.add(constructRangeString(nums, low, high - 1));
                // The next range starts at the current high
                low = high;
            }
            
            high++;
        }
        
        // Construct the final range
        outputList.add(constructRangeString(nums, low, high - 1));
        
        return outputList;
    }
    
    // Helper method to create the output string for a given range
    private String constructRangeString(int[] nums, int low, int high) {
        if (low == high) {
            return Integer.toString(nums[low]);
        } else {
            return nums[low] + "->" + nums[high];
        }
    }
}