class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> output = new ArrayList<String>();
        
        // Base Case - If the array is empty, return the range from lower to upper
        if (nums.length == 0) {
            output.add(constructStringForRange(lower, upper));
            return output;
        }
        
        
        // Iterate from the start until we find or pass the lower number
        int start = 0;
        while (start < nums.length && nums[start] < lower) {
            start++;
        }
        
        // If the lower bound isn't in the array, create our first output string.
        if (nums[start] != lower) {
            output.add(constructStringForRange(lower, nums[start] - 1));
        }
        
        // Iterate from the start index until we reach the end of the array
        // Or until the current value is >= the upper bound.
        int index = start + 1;
        while (index < nums.length && nums[index] <= upper) {
            // If the number at the current index is not equal to the number at the 
            // previous index plus 1. Then we have a missing range.
            if (nums[index] != nums[index - 1] + 1) {
                output.add(constructStringForRange(nums[index - 1] + 1, nums[index] - 1));
            }
            index++;
        }
        
        // Finally, if the upper bound isn't in the array, create our last output string
        index = index == nums.length ? index - 1 : index;
        if (nums[index] < upper) {
            output.add(constructStringForRange(nums[index] + 1, upper));
        }
        
        return output;
    }
    
    private String constructStringForRange(int start, int end) {
        if (start == end) return Integer.toString(start);
        
        return start + "->" + end;
    }
}