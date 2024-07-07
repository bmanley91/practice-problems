class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // Brute force would be to look at temp[i]
        /// and iterate until we find temp[j] > temp[i]
        
        // Go backwards through the array
        // Keep track of the most recent index we've seen for each temp in a map
        // At each temp[i], iterate through the temps we've seen so far
        /// ans[i] = min(tempIndexMap.get(x) - i) where x > temp[i]
        // If we iterate through all temps and find none > temp[i]
        /// then ans[i] = 0

        Map<Integer, Integer> tempIndexMap = new HashMap<Integer, Integer>();
        int n = temperatures.length;
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int currentTemp = temperatures[i];
            int minDistance = Integer.MAX_VALUE;

            // Iterate through previous temperatures
            for (int temp : tempIndexMap.keySet()) {
                if (temp > currentTemp) {
                    minDistance = Integer.min(
                        minDistance,
                        tempIndexMap.get(temp) - i
                    );
                }
                
            }

            if (minDistance == Integer.MAX_VALUE) {
                ans[i] = 0;
            } else {
                ans[i] = minDistance;
            }

            tempIndexMap.put(currentTemp, i);
        }
        return ans;
    }
}
