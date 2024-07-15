class Solution {
    public int climbStairs(int n) {
        // Base Case for 1 or fewer stairs
        if (n < 2) return n;
        
        // Since we can take one or two steps at a time, the number of ways you can get to step n is:
        // (ways to get to step n - 1) + (ways to get to step n - 2)
        // There's 0 ways to get to step 0
        // There's 1 way to get to step 1.
        // There's 2 ways to get to step 2.
        // There's 3 ways to get to step 3: 2 + 1 -> (1,2), (2,1), (1,1,1)
        // There's 5 ways to get to step 4: 3 + 2 -> (2,2), (2,1,1), (1,2,1), (1,1,2), (1,1,1,1,1)
        
        // Store the number of ways to get to a step in an array so that when we 
        // calculate the next step we can use our previous results
        int[] dp = new int[n + 1];
        // Steps 1 and 2 are our DP base cases (0 here for sanity)
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        int i = 3;
        while (i <= n) {
            dp[i] = dp[i - 1] + dp[i - 2];
            i++;
        }
        
        return dp[n];
    }
}