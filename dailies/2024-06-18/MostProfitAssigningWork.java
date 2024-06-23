class Solution {
    class Pair implements Comparable<Pair> {
        int difficulty;
        int profit;

        Pair(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.difficulty, other.difficulty);
        }
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        // For each worker j, find the i with the highest profit[i] 
        /// where worker[j] >=difficulty[i]

        // dp - find the highest profitability at any given difficulty
        // Then check dp[worker[j]] to get the output for each worker
        // Need to find the highest difficulty our workers can handle
        int maxWorker = 0;
        for (int worker : workers) {
            maxWorker = Integer.max(worker, maxWorker);
        }

        List<Pair> workPairs = new ArrayList<Pair>();
        for (int i = 0; i < difficulty.length; i++) {
            workPairs.add(new Pair(difficulty[i], profit[i]));
        }
        Collections.sort(workPairs);
        
        // Build the dp array
        int[] highestProfitForDifficulty = new int[maxWorker + 1];
        int currentMaxProfit = 0;
        int currentPair = 0;
        for (int i = 0; i <= maxWorker; i++) {
            if (currentPair < workPairs.length) {
                while (i < workPairs.get(currentPair).difficulty && i < maxWorker) {
                    highestProfitForDifficulty[i] = currentMaxProfit;
                    i++;
                }
                currentPair++;
                if (currentPair < workPairs.size()) {
                    currentMaxProfit = Integer.max(
                        currentMaxProfit,
                        workPairs.get(currentPair).profit;
                    )
                }
            } else {
                highestProfitForDifficulty[i] = currentMaxProfit;
            }
        }

        // Iterate through workers and return their totaled max profit
        int output = 0;
        for (int worker : workers) {
            output += highestProfitForDifficulty[worker];
        }
        return output;
    }
}
