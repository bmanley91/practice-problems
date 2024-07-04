class Solution {
    public int minDifference(int[] nums) {
        // Sort
        // [1,5,0,10,14] -> [0,1,5,10,14]
        // look at biggest and smallest  

        // If nums.length <= 4 we can always make all elements the same
        if (nums.length <= 4) return 0;

        Arrays.sort(nums);

        // Figure out which number we can change to have the biggest impact on the difference
        // Keep a running sum / average of the numbers
        // See if the largest or smallest value is further from the average
        // I guess it doesn't actually matter what we change a number too
        // So maybe we can just treat them as being removed?

        // [0,1,5,10,14] avg: 6 -> 14 is the furthest, remove it
        // [0,1,5,10] avg: 5 -> 10 is the furthest, remove it
        // [0,1,5] avg: 2 -> 5 is the furthest, remove it
        // [0,1] -> output 1

        // [2,3,4,5] avg: 3 -> remove 5
        // [2,3,4] avg: 4 -> remove 4
        // [2,3] avg: 2 -> remove 3
        // [2] -> output 0

        // Rather than looking at the average, we could probably 
        // look at the biggest diff between moving left or right inward
        // NOPE - this didn't work lol
        // Copying failed code up here
        /* 
            int left = 0;
        int right = nums.length - 1;
        int counter = 0;
        while (left < right && counter < 3) {
            int leftDiff = nums[left+1] - nums[left];
            int rightDiff = nums[right] - nums[right-1];

            if (leftDiff > rightDiff) {
                left++;
            } else {
                right--;
            }

            counter++;
        }

        return nums[right] - nums[left];
        */

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // "Remove" can be a two pointer operation where we just move the pointer inward
        int left = 0;
        int right = nums.length - 1;
        int counter = 0;
        while (left < right && counter < 3) {
            int currentAverage = sum / (nums.length - counter);
            int leftDiff = currentAverage - nums[left];
            int rightDiff = nums[right] - currentAverage;

            System.out.println("Average " + currentAverage + " left " + nums[left] + " right "+ nums[right]);

            // "Remove" the element that's further from the average
            // Decrement the sum by the removed element so that we have an accurate average
            if (leftDiff >= rightDiff) {
                System.out.println("Removing " + nums[left]);
                sum -= nums[left];
                left++;
            } else {
                System.out.println("Removing " + nums[right]);
                sum -= nums[right];
                right--;
            }

            counter++;
        }

        return nums[right] - nums[left];
    }
    // [6,6,0,1,1,4,6] -> sort -> [0,1,1,4,6,6,6] avg: 3
    // [1,1,4,6,6,6] avg: 4
    // [1,4,6,6,6] avg: 5
    // [4,6,6,6] -> output 2
}
