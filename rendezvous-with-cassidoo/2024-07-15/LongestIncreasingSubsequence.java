import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static void main(String args[]) {
        int[] input1 = new int[]{10, 9, 2, 3, 7, 101, 18};
        System.out.println(
            "Input: " + Arrays.toString(input1) + 
            ", expected: 4, actual: " + 
            longestIncreasingSubsequence(input1)
        );

        int[] input2 = new int[]{4, 4, 4, 4, 3};
        System.out.println(
            "Input: " + Arrays.toString(input2) + 
            ", expected: 1, actual: " + 
            longestIncreasingSubsequence(input2)
        );

        int[] input3 = new int[]{1};
        System.out.println(
            "Input: " + Arrays.toString(input3) + 
            ", expected: 1, actual: " + 
            longestIncreasingSubsequence(input3)
        );

        int[] input4 = new int[]{};
        System.out.println(
            "Input: " + Arrays.toString(input4) + 
            ", expected: 0, actual: " + 
            longestIncreasingSubsequence(input4)
        );
    }
    
    private static int longestIncreasingSubsequence(int[] input) {
        // Base case for array that's too small to process
        if (input.length < 2) return input.length;

        int max = 0;
        int start = 0;
        int curr = 1;
        while (curr < input.length) {
            // If the current number is less than the one before it,
            // Then start a new subsequence
            if (input[curr] < input[curr-1]) {
                start = curr;
            }
            curr++;
            max = Integer.max(
                curr - start,
                max
            );
        }

        return max;
    }
}
