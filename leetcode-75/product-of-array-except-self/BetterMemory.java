class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Pass from left to right
        // Then pass from right to left 
        int answer[] = new int[nums.length];
        Arrays.fill(answer, 1);
        int curr = 1;
        // Store the left product in answer[i]
        // Then update curr so that nums[i] is used when calculating answer[i+1]
        for (int i = 0; i < nums.length; i++) {
            answer[i] *= curr;
            curr *= nums[i];
        }
        
        // Reset curr for our pass from the right
        curr = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] *= curr;
            curr *= nums[i];
        }
        return answer;
    }
}
