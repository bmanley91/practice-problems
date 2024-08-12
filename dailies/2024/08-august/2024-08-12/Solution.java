import java.util.PriorityQueue;

class KthLargest {
    // Since we're only adding to the list of numbers
    // We can use a min heap of size k
    // When a number is added:
    // If the heap is less than k size, add the number to the heap
    /// Otherwise, check if it is bigger than the top of the heap
    /// If it is, add it to the heap and poll the heap.
    // Return the top of the heap

    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<Integer>();

        for (int num : nums) {
            if (this.minHeap.isEmpty() || this.minHeap.size() < k) {
                this.minHeap.add(num);
            } else if (num > this.minHeap.peek()) {
                this.minHeap.poll();
                this.minHeap.add(num);
            }
        }
    }
    
    public int add(int val) {
        if (this.minHeap.size() < this.k) {
            minHeap.add(val);
        } else if (val > this.minHeap.peek()) {
            minHeap.poll();
            minHeap.add(val);
        }
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
