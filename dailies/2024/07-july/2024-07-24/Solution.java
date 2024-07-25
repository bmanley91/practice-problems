import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        // More sorting?!
        // Create a Map of source to destination number
        /// Creating this map up front will give us some efficiency when sorting later 
        //// so that we don't need to re-map every time we see a number
        // Implement a custom sort using the destination number as the comparison point.

        Map<Integer, Integer> mappedNumbers = mapNumbers(mapping, nums);

        nums = Arrays.stream(nums)
            .boxed()
            .sorted((a, b) -> 
                Integer.compare(
                    mappedNumbers.get(a), 
                    mappedNumbers.get(b)
                ))
            .mapToInt(i -> i)
            .toArray();

        return nums;
    }

    private Map<Integer, Integer> mapNumbers(int[] mapping, int[] nums) {
        Map<Integer, Integer> numberTranslations = new HashMap<Integer, Integer>();
        
        for (int num : nums) {
            // If we've already mapped a number, don't map it again
            if (!numberTranslations.containsKey(num)) {

                StringBuilder builder = new StringBuilder();
                char[] numString = Integer.toString(num).toCharArray();
                for (char c : numString) {
                    int digit = c - '0';
                    int mappedDigit = mapping[digit];
                    // Don't append leading mapped zeros to the output
                    if (!(builder.length() == 0 && mappedDigit == 0)) {
                        builder.append(mappedDigit);
                    }
                }

                if (builder.length() == 0) {
                    builder.append(0);
                }
                numberTranslations.put(num, Integer.valueOf(builder.toString()));
            }
        }

        return numberTranslations;
    }
}