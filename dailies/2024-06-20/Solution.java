class Solution {
    public int maxDistance(int[] position, int m) {
        // m will always be between 2 and position.length
        // Solve for base case
        // If m = position.length then the max min force is the min distance between buckets
        // [100, 50, 25, 10], m=4 -> [10, 25, 50, 100]
        // return 15
        // We definitely need to sort first
        // We also probably always use the first and last
        Arrays.sort(position);

        // Binary search for a min distance mid where we can successfully place all balls
        // If we can, then try for a larger distance
        // If we cannot, try for a smaller distance
        // Search criteria:
        /// Start at first position in sorted array
        /// Check if abs(position[1] - position[0]) >= mid
        //// If not, check postion[2] and position[0], etc.
        
        int maxMinDistance = 0;
        int low = 1;
        int high = position[position.length - 1];
        
        while(low <= high) {
            int mid = low + ((high-low)/2);
            if (doesDistanceWork(position, mid, m)) {
                // Check bigger
                low = mid+1;
                maxMinDistance = mid;
            } else {
                // Check smaller
                high = mid-1;
            }
        }

        return maxMinDistance;
    }

    private boolean doesDistanceWork(int[] position, int distance, int ballCount) {
        
        int countSoFar = 1;
        int last = position[0];

        for (int i = 0; i< position.length; i++) {
            if (position[i] - last >= distance) {
                countSoFar++;
                last = position[i];
            }

            if (countSoFar >= ballCount) {
                return true;
            }
        }
        return false;
    }
}
