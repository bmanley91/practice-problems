class Solution {
    public int coinChange(int[] coins, int amount) {
        // Using an array, create a "table" of amounts to the minumum number 
        // of coins required to make that amount
        // The array index is the "key" and the coin count is the "value"
        int[] table = new int[amount + 1];
        // We fill our table with an initial value so that we can easily see 
        // if an amount has a coin count associated with it.
        Arrays.fill(table, -1);
        table[0] = 0;
        
        // Loop over each coin
        for (int coin : coins) {
            // Then loop over each amount in the table
            for (int i = coin; i < table.length; i++) {
                if (table[i - coin] != -1) {
                    int currentCoinCount = 1 + table[i - coin];
                    
                    // If there's no entry for the given amount, use the current one
                    // Otherwise use the lower of the current and previous count.
                    if (table[i] == -1) {
                        table[i] = currentCoinCount;
                    } else {
                        table[i] = Math.min(table[i], currentCoinCount);
                    }
                }
            }
        }
        
        // Return the value for the target amount that's stored in our table.
        return table[amount];
    }
}