class Solution {
    // 3 different potential scenarios
    // Buy/Sell twice
    // Buy/Sell once
    // Don't Buy/Sell at all
    public int maxProfit(int[] prices) {
        int firstCost = Integer.MAX_VALUE;
        int firstProfit = 0;
        int secondCost = Integer.MAX_VALUE;
        int secondProfit = 0;
        
        // Iterate through all of the prices
        for (int i = 0; i < prices.length; i++) {
            firstCost = Math.min(firstCost, prices[i]);
            firstProfit = Math.max(firstProfit, prices[i] - firstCost);
            
            // Instead of just looking at the current price for the second cost
            // We need to evaluate if it's worth making a second trade.
            secondCost = Math.min(secondCost, prices[i] - firstProfit);
            secondProfit = Math.max(secondProfit, prices[i] - secondCost);
        }
        
        return Math.max(firstProfit, secondProfit);
    }
}