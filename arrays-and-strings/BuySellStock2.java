class Solution {
    // If we're allowed to complete as many transactions as possible, 
    // then every time a price is less than the following price, we should buy then sell the next day.
    // In the case of [1, 2, 3, 1], following this approach, we will both sell and then buy at price 2,
    // This isn't allowed, but the end result is the same as buying at 1 and selling at 3.
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                maxProfit += prices[i + 1] - prices[i];
            }
        }
        
        return maxProfit;
    }
}