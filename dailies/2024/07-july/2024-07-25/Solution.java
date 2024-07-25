class Solution {
    public int[] sortArray(int[] nums) {
        // Heap Sort
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();

        for (int num : nums) {
            heap.add(num);
        }

        int i = 0;
        while (!heap.isEmpty()) {
            nums[i] = heap.poll();
            i++;
        }
        return nums;
    }

    public int[] mergeSort(int[] nums) {
        return nums;
    }
}