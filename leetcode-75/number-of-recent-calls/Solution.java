import java.util.LinkedList;
import java.util.Queue;

class RecentCounter {
    private Queue<Integer> previousCalls;
    private final int OFFSET = 3000;

    public RecentCounter() {
        this.previousCalls = new LinkedList<Integer>();
    }
    
    public int ping(int t) {
        // Keep track of call times in a queue
        // When a call comes in, look at the front of the queue.
        // If the element at the front of the queue is less than t - 3000,
        /// remove it from the queue.
        /// Repeat until the queue is empty or the element at the front of the queue is within t - 3000
        // Return the size of the queue as the number of calls that have happened within 3000

        // We count the current call so add t to the queue
        previousCalls.add(t);

        while(previousCalls.peek() < t - OFFSET) {
            previousCalls.poll();
        }
        return previousCalls.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
