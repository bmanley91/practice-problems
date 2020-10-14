class HouseRobber {
    public int rob(int[] nums) {
        // DP approach
        // Keep track of two "maxes" - the max looking at the current house, and the max at the previous house.
        // Loop over every house and return the highest sum that we find.
        // This is a bit different than a "typical" DP approach, we're not storing all of the values in an array.
        // We probably could store previous maximums in an array and then look at them as we go.
        int currentMaxSum = 0;
        int previousMaxSum = 0;
        
        // Loop through all houses
        for (int i = 0; i < nums.length; i++) {
            // We use a temp var to hold the max from the previous iteration
            // We assign this value to "previous" at the end of the loop. 
            // This assures that previous stays 2 places behind i.
            int temp = currentMaxSum;
            
            // The highest value at a given house is either the current value plus the max two houses ago 
            // or the max at the house just before the current one.
            currentMaxSum = Math.max(previousMaxSum + nums[i], currentMaxSum);
            
            previousMaxSum = temp;
        }
        
        return currentMaxSum;
    }
}