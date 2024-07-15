class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] returnVal = new int[2];
        
        // Create a map of each number to its index in the array. 
        // We'll look back at this map for each number we come across in the array 
        // and see if the difference between the current number and the target is in the map.
        Map<Integer, Integer> numberIndexMap = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int diffToTarget = target - nums[i];
            if (numberIndexMap.get(diffToTarget) != null) {
                returnVal[0] = numberIndexMap.get(diffToTarget);
                returnVal[1] = i;
                return returnVal;
            }
            
            numberIndexMap.put(nums[i], i);
        }
        return returnVal;
    }
}