class Solution {
    // Loop through the array backwards.
    // Keep track of what needs to be added in a "carry" variable
    // If we reach the end of the array and there's still a carry,
    // then create a new array with one more element, put the carry at index 0 
    // and copy the rest of the other array into the result.
    public int[] plusOne(int[] digits) {
        int[] output = new int[digits.length];
        
        // Loop through the array backwards.
        // Keep track of what needs to be added in a "carry" variable
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int newVal = digits[i] + carry;
            if (newVal >= 10) {
                newVal -= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            output[i] = newVal;
        }
        
        if (carry > 0) {
            // Slight tweak to the original plan.
            // Since we're only adding 1, the only posibbility when there's a carry 
            // left is that every non-leading number is 0.
            output = new int[digits.length + 1];
            output[0] = carry;
        }
        return output;
    }
}