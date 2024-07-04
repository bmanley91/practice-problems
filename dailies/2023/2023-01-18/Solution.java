class Solution {
public int maxSubarraySumCircular(int[] nums) {
        // Do one pass to populate a max sums dp array.
        // Keep track of the highest max sum so far.
        // Then do a second pass with the final max sum value as the base
        // During the second pass, check if the new max sum is less than the previous max sum.
        // If it is, then we should stop processing and return the highest max number seen.
        // Need to keep track of indexes of start and end so we don't double use a given number on the second pass
        int[] maxSumArray = new int[nums.length];
        maxSumArray[0] = nums[0];
        int highestSum = nums[0];
        int[] startIndexesOfMaxSubArray = new int[nums.length];
        startIndexesOfMaxSubArray[0] = 0;

        // First pass to figure out basic continuous sum
        for (int i = 1; i < nums.length; i++) {
            // Figure out the max sum at this point
            int currentNum = nums[i];
            int currentSum = maxSumArray[i-1] + nums[i];
            if (currentNum > currentSum) {
                currentSum = currentNum;
                startIndexesOfMaxSubArray[i] = i;
            } else {
                startIndexesOfMaxSubArray[i] = startIndexesOfMaxSubArray[i-1];
            }

            // Check against the max sum so far
            highestSum = Math.max(currentSum, highestSum);

            // Put the max sum at this point in the sum array
            maxSumArray[i] = currentSum;

            // System.out.println("Highest sum at point " + i + " is " + currentSum);
        }

        


        
        System.out.println("Returning " + highestSum);
        return highestSum;
    }
};


        // Second pass to find wraparound sum
        // Use the last number in the max sum array as the base
        // int wraparoundBase = maxSumArray[maxSumArray.length - 1];
        // int wraparoundFirstValue = maxSumArray[0] + wraparoundBase;
        // // Only do the second pass if wraparound is viable
        // if (wraparoundFirstValue >= maxSumArray[0] && startIndexesOfMaxSubArray[maxSumArray.length - 1] != 0) {
        //     maxSumArray[0] = wraparoundFirstValue;
        //     for (int i = 1; i < nums.length; i++) {
        //         if (startIndexesOfMaxSubArray[i] == i) {
        //             break;
        //         }


        //         int maxSumWithWraparound = Math.max(maxSumArray[i - 1], nums[i]);

        //         highestSum = Math.max(maxSumWithWraparound, highestSum);

        //         maxSumArray[i] = maxSumWithWraparound;
        //     }
        // }
