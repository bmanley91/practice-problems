class Solution {
    public int[] productExceptSelf(int[] nums) {
        // Sweep to get products from left and right
        // left[i] = nums[i-1] * left[i-1]
        // right[i] = nums[i+1] * right[i+1] 
        
        int[] left = new int[nums.length];
        left[0] = 1;
        int[] right = new int[nums.length];
        right[nums.length - 1] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i-1] * nums[i-1];
            int rightIndex = nums.length - 1 - i;
            right[rightIndex] = right[rightIndex + 1] * nums[rightIndex + 1];
        }

        int[] answer = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            answer[i] = left[i] * right[i];
        }
        return answer;
    }
}
