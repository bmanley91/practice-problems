class Solution {
    public int[][] kClosest(int[][] points, int K) {
        // Store points in a min heap, where the heap comparator is the distance to the origin.
        // Distance = sqrt(point[0]^2 + point[1]^2)
        // Extract the top elements from the heap until we have an output of size K

        // Min Heap with custom comparator based on calculated sitance
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a, b) -> {
            double aDistance = calculateDistanceForPoint(a);
            double bDistance = calculateDistanceForPoint(b);
            
            if (aDistance < bDistance) {
                return -1;
            } else if (aDistance > bDistance) {
                return 1;
            } else {
                return 0;
            }
        });
        
        for (int[] point : points) {
            minHeap.add(point);
        }
        
        int[][] output = new int[K][2];
        for (int i = 0; i < K; i++) {
            output[i] = minHeap.poll();
        }
        
        return output;
    }
    
    private double calculateDistanceForPoint(int[] point) {
        return Math.sqrt(point[0] * point[0] + point[1] * point[1])
    }
}