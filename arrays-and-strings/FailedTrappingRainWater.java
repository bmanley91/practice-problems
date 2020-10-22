class Solution {
    public int trap(int[] height) {
        int output = 0;
        
        // Two Pointers
        // p1 looks for peaks or the beginnign of a "basin"
        // p2 scans ahead of p1 to see if we "complete" a basin
        int p1 = 0;
        int p2 = 0;
        
        while(p1 < height.length - 1) {
            // Advance p1 until we reach a peak
            if (height[p1] < height[p1 + 1]) {
                // System.out.println("Advaincing p1");
                p1++;
            } else {
                p2 = p1 + 1;
                // Advance p2 until we reach a point that is equal to or higher than p1
                // or until p2's height is between the heights of p1 and (p1 + 1)
                while (p2 < height.length - 1) { // Stop advancing if we reach the end of the list
                    if (height[p2] > height[p1] || height[p2] > height[p1 + 1]) {
                        break;
                    }
                    // System.out.println("Advancing p2");
                    // System.out.println("p2: " + height[p2]);
                    // System.out.println("p1: " + height[p1]);
                    // System.out.println("p1+1 " + height[p1 + 1]);
                    p2++;
                }
                
                // If we reach the end of the list and we haven't found a peak 
                // then advance p1 and keep going
                if (p2 == height.length -1) {
                    p1++;
                } else {
                    // p1 and p2 now span a basin.
                    // We use these pointers to figure out how much water is collected between them.
                    output += findWaterInRange(p1, p2, height);
                    System.out.println("Output is now " + output);

                    // Basin is completed. Advance p1 to p2.
                    p1 = p2;
                }
            }
        }
        
        return output;
    }
    
    private int findWaterInRange(int start, int end, int[] heightArray) {
        int totalWater = 0;
            
        int maxWaterHeight = Math.min(heightArray[start], heightArray[end]);
        for (int i = start + 1; i < end; i++) {
            totalWater += maxWaterHeight - heightArray[i];
        }
        
        if (totalWater < 0) totalWater = 0;
        
        System.out.println("There is " + totalWater + " between " + start + " and " + end);
        return totalWater;
    }
}