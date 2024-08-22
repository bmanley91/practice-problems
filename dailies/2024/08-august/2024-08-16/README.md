# Maximum Distance in Arrays
https://leetcode.com/problems/maximum-distance-in-arrays

class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        // Simple case:
        // Look at the first element in each list and find the smallest
        // Look at the last element in each list and find the biggest
        // Return the difference

        // But we need to pick numbers from separate arrays
        // So if we have A and B
        // Where A has the smallest and largest element
        // We need to see if B[last] - A[0] or A[last] - B[0] is larger
        // Use Heaps for largest and smallest
        // Put list indexes in the heaps with a custom comparator which looks at the first/last
        //      elements in the corresponding list

        // Order indexes of each array by the first element in the array
        PriorityQueue<Integer> minIndexes = new PriorityQueue(
            Comparator.comparing(i -> arrays.get((int) i).get(0))
        );

        // Order indexes of each array by the last element in the array
        PriorityQueue<Integer> maxIndexes = new PriorityQueue(
            Comparator.comparing(i -> arrays.get((int) i).get(arrays.get((int) i).size() - 1)).reversed()
        );

        for (int i = 0; i < arrays.size(); i++) {
            minIndexes.add(i);
            maxIndexes.add(i);

            // System.out.println("Min top: " + minIndexes.peek());
            // System.out.println("Max top: " + maxIndexes.peek());
        }

        
        // If the indexes at the top of each heap are different, then just return the difference
        if (minIndexes.peek() != maxIndexes.peek()) {
            int maxIndex = maxIndexes.peek();
            int minIndex = minIndexes.peek();
            int highest = arrays.get(maxIndex).get(arrays.get(maxIndex).size() - 1);
            int lowest = arrays.get(minIndex).get(0);

            return highest - lowest;
        } 
        // If the biggest and smallest elements are in the same array
        // We need to compare with the next biggest and next smallest
        else {
            int maxIndex = maxIndexes.poll();
            int secondMaxIndex = maxIndexes.peek();
            int minIndex = minIndexes.poll();
            int secondMinIndex = minIndexes.peek();

            int highest = arrays.get(maxIndex).get(arrays.get(maxIndex).size() - 1);
            int secondHighest = arrays
                .get(secondMaxIndex)
                .get(arrays.get(secondMaxIndex).size() - 1);

            int lowest = arrays.get(minIndex).get(0);
            int secondLowest = arrays.get(secondMinIndex).get(0);
            
            return Math.max(
                highest - secondLowest,
                secondHighest - lowest
            );
        }
    }
}