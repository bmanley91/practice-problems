import java.util.HashMap;
import java.util.Map;

class Solution {
    public String kthDistinct(String[] arr, int k) {
        // Use a map of strings to how many times they appear
        // On a second pass, iterate through input in order and return the kth element with a count of 1
        Map<String, Integer> stringCounts = new HashMap<String, Integer>();
        for (String s : arr) {
            stringCounts.put(
                s,
                stringCounts.getOrDefault(s, 0) + 1
            );
        }

        int counter = 0;
        for (String s : arr) {
            if (stringCounts.get(s) == 1) {
                counter++;
                if (counter == k) {
                    return s;
                }
            }
        }
        
        return "";
    }
}
