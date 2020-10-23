/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */

class Solution {
    // Binary Search with no upper bound.
    // Find upper bound by increasing by power of 2 until we go past the target.
    // Once we pass the target, we know that the target must be between 
    // Math.pow(2, current) and Math.pow(2, current - 1)
    // Once we've found bounds, binary search within them.
    public int search(ArrayReader reader, int target) {
        // Base case - the ArrayReader is empty
        if (reader.get(0) == Integer.MAX_VALUE) return -1;
        
        // Find upper bound by increasing by power of 2 until we go past the target.
        int currentPower = 0;
        while(reader.get((int) Math.pow(2, currentPower)) < target) {
            currentPower++;
        }
        
        // Once we pass the target, we know that the target must be between 
        // Math.pow(2, current) and Math.pow(2, current - 1)
        // Once we've found bounds, binary search within them.
        int min = (int) Math.pow(2, currentPower - 1);
        int max = (int) Math.pow(2, currentPower);
        while (min <= max) {
            int mid = min + ((max - min) / 2);
            int currentNumber = reader.get(mid);
            
            if (currentNumber > target) {
                // If the number is too high, go left
                max = mid - 1;
            } else if (currentNumber < target) {
                // If the number is too log, go right
                min = mid + 1;
            } else {
                // If the number isn't too high or too low, it must be the target.
                return mid;
            }
        }
        
        // If we get here, we've exhausted our search without finding the target.
        return -1;
    }
}