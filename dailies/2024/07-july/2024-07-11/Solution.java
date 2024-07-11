class Solution {
    // Keep track of a running index as a class variable so that we 
    /// don't need to worry about passing it into and returning it from the recursive function
    private int i = 0;

    public String reverseParentheses(String s) {
        return reverseParensRecursive(s.toCharArray());
    }

    private String reverseParensRecursive(char[] chars) {
        StringBuilder builder = new StringBuilder();

        while (i < chars.length) {
            // If we find an open paren then we need to recursively reverse this substring
            if (chars[i] == '(') {
                i++;
                builder.append(
                    reverseParensRecursive(chars)
                );
            } 
            // If we find a close paren, we're done with this recursion.
            /// Reverse the string we've built so far and return it.
            else if (chars[i] == ')') {
                i++;
                return builder.reverse().toString();
            }
            // For any other char, just append it to the string we're building.
            else {
                builder.append(chars[i]);
                i++;
            }
        }
        // If we reach the end of the input, return the string we've built.
        return builder.toString();
    }
}