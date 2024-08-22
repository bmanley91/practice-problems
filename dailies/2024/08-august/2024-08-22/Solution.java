class Solution {
    public int findComplement(int num) {
        // Convert to binary string
        String binaryString = Integer.toBinaryString(num);
        
        // Original solution - Used more memory since we had to use 
        //      an additional StringBuilder and String.
        // Iterate through binary string and flip each bit
        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i < binaryString.length(); i++) {
        //     if (binaryString.charAt(i) == '0') {
        //         sb.append('1');
        //     } else {
        //         sb.append('0');
        //     }
        // }
        // Convert new string into number
        // return Integer.parseInt(sb.toString(), 2);

        // Better solution - Uses less memory and is faster
        int output = 0;
        for (int i = binaryString.length() - 1; i >= 0; i-- ) {
            int power = binaryString.length() - 1 - i;
            // If the current char is 0, we want to flip this bit, so add the appropriate power of 2
            if (binaryString.charAt(i) == '0') {
                output += Math.pow(2, power);
            }
        }

        // Convert new string into number
        return output;
    }
}