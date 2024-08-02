class Solution {
    public int countSeniors(String[] details) {
        // Parse each string
        // "7868190130M7522" ->
        /// phone: substr(0,10) -> "7868190130"
        /// gender: charAt(10) -> 'M'
        /// age: substr(11, 13) -> "75"
        /// seat: substr(13, 15) -> "22"
        
        // We only actually care about the age for this problem
        // So we can just extract the age value from the string, convert it to a number, and compare it to 60

        // Edge case considerations
        // Input array will have at least one element
        // Strings are guaranteed to be 15 chars long

        int count = 0;
        for (int i = 0; i < details.length; i++) {
            String ageString = details[i].substring(11, 13);

            if (Integer.valueOf(ageString) > 60) {
                count++;
            }
        }
        return count;
    }
}