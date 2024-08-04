import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int rangeSum(int[] nums, int n, int left, int right) {
        // This one seems kinda goofy?
        // Produce the sums
        List<Long> subArraySums = getSubArraySums(nums, n);
        
        // Sort the sums
        Collections.sort(subArraySums);

        // Sum from left to right indexes in the new array
        long output = 0;
        for (int i = left - 1; i < right; i++) {
            output += subArraySums.get(i);
        }

        return (int) (output % 1000000007);
    }

    private List<Long> getSubArraySums(int[] nums, int n) {
        List<Long> subArraySums = new ArrayList<Long>();

        // Dynamic programming!
        // 2D array sums
        // sums[i][j] = the sum of the subarray from i to j
        // for each i, we look at all the sums which came before 
        long[][] sums = new long[n+1][n+1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    sums[i][j] = nums[i];
                } else {
                    sums[i][j] = sums[i-1][j] + nums[i];
                }
                subArraySums.add(sums[i][j]);
            }
        }
        return subArraySums;
    }
}
