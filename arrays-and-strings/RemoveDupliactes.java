class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        // Two pointers:
        // Fast keeps advancing
        // Slow only advances when fast encounters a new number
        // When fast encounters a new number, advance slow by one slot, 
        // and put the value at fast at slow.
        // At the end slow should be the index of the last number.
        int slow = 0;
        int fast = 0;
        for (; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        
        // We have to add 1 at the end because the problem asks 
        // for _length_ not the index of the last 
        return slow + 1;
    }
}