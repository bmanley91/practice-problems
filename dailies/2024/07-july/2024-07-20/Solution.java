import java.util.PriorityQueue;

class Solution {
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        // Find the smallest rowSum and colSum whichever is lower 
        // place the number in the grid and subtract from the other

        // Edge case considerations
        /// Guaranteed to have at least one element
        /// sum[x] can be 0

        // Create a min heap for rows and cols
        // keep processing until both heaps are empty
        // At the end of the loop, there will be one element left in one of the queues,
        /// but that doesn't matter because the final elements in each heap will have the same sum value.
        /// So we write to the output and ignore the leftover element.
        PriorityQueue<SumPair> rowHeap = new PriorityQueue<SumPair>();
        PriorityQueue<SumPair> colHeap = new PriorityQueue<SumPair>();
        
        // Populate heaps
        for (int i = 0; i < rowSum.length; i++) {
            rowHeap.add(new SumPair(rowSum[i], i));
        }
        for (int i = 0; i < colSum.length; i++) {
            colHeap.add(new SumPair(colSum[i], i));
        }

        int[][] output = new int[rowSum.length][colSum.length];

        while (
            rowHeap.size() > 0 &&
            colHeap.size() > 0
        ) {
            SumPair rowPair = rowHeap.poll();
            SumPair colPair = colHeap.poll();

            // We take the smaller number and write it to the output
            // Then subtract the smaller sum from the larger and put it back in its heap
            if (rowPair.sum < colPair.sum) {
                output[rowPair.index][colPair.index] = rowPair.sum;
                colPair.sum -= rowPair.sum;
                colHeap.add(colPair);
            } else {
                output[rowPair.index][colPair.index] = colPair.sum;
                rowPair.sum -= colPair.sum;
                rowHeap.add(rowPair);
            }
        }
        return output;
    }

    class SumPair implements Comparable<SumPair> {
        int sum;
        int index;

        SumPair (int sum, int index) {
            this.sum = sum;
            this.index = index;
        }

        @Override
        public int compareTo(SumPair other) {
            return Integer.compare(this.sum, other.sum);
        }
    }
}
