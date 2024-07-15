class Solution {
    
    public int minMeetingRooms(int[][] intervals) {
        // Base Case - If there are no meetings we need 0 rooms.
        if (intervals.length == 0) return 0;
        
        // Sort the intervals by start time ascending.
        // Create a Min Heap that will hold end times.
        // The end times in this Heap will essentially hold our meetings in progress.
        // Iterate through the meetings:
        // If the current meeting's start time is after the end time at the top of the heap, 
        // remove it from the heap.
        // Either way add the current meeting's end time to the heap since it's now in progress.
        // Once we've exhausted all of our intervals, return the size of the queue.
        // Each entry in the queue is a room that never freed up before another meeting needed it.
        
        // Min Heap definition - PQs are in ascending order by default.
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        
        // Sort the array by start time ascending
        Arrays.sort(
            intervals,
            new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return a[0] - b[0];
                }
            }
        );
        
        // Add the first room to the heap
        minHeap.add(intervals[0][1]);
        
        // Loop over the rest of the meetings
        for (int i = 1; i < intervals.length; i++) {
            // If the current meeting's start time is after the end time at the top of the heap, 
            // remove it from the heap.
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            
            // Either way add the current meeting's end time to the heap since it's now in progress.
            minHeap.add(intervals[i][1]);
        }
        
        return minHeap.size();
    }
        
}