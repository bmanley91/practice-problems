class Solution {
    // Iterate through the list and put each element into a max heap.
    // After putting each element into the heap, extract elements until we reach the kth.
    public int findKthLargest(int[] nums, int k) {
        // Max Heap definition
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> b - a);
        
        for (int num : nums) {
            maxHeap.add(num);
        }
        
        int highestAtIndex = 0;
        for (int i = 0; i < k; i++) {
            highestAtIndex = maxHeap.poll();
        }
        
        return highestAtIndex;
    }
}