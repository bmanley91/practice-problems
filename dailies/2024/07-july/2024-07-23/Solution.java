import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    class CountPair implements Comparable<CountPair> {
        int number;
        int count;

        public CountPair(int number, int count) {
            this.number = number;
            this.count = count;
        }

        @Override
        public int compareTo(CountPair other) {
            if (this.count == other.count) {
                // If counts are the same, sort by number decreasing
                return Integer.compare(other.number, this.number);
            } else {
                // Sort by count increasing
                return Integer.compare(this.count, other.count);
            }
        }
    }
    public int[] frequencySort(int[] nums) {
        // Three Passes
        // Get counts of each number
        // Sort count pairs by count then number value
        // Write to output

        Map<Integer, Integer> numberCounts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            numberCounts.put(
                num,
                numberCounts.getOrDefault(num, 0) + 1
            );
        }

        PriorityQueue<CountPair> sortedPairs = new PriorityQueue<CountPair>();
        for (int key : numberCounts.keySet()) {
            sortedPairs.add(new CountPair(
                key,
                numberCounts.get(key)
            ));
        }

        int[] output = new int[nums.length];
        int index = 0;
        while (!sortedPairs.isEmpty()) {
            CountPair pair = sortedPairs.poll();
            int count = pair.count;
            while(count > 0) {
                output[index] = pair.number;
                index++;
                count--;
            }
        }
        return output;
    }
}