import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // Stack keeps track of days "waiting" for a warmer day
        /// Store index in the stack
        // At each temp[i], compare the temp value to the value at the index (x) on top of the stack
        // If the current temp is higher, then write ans[x] = i - x
        /// Pop this value off the stack
        /// Continue until either the stack is empty or temp[i] <= temp[x]
        // Push i onto the stack so we can look for ans[i]
        // At the end, pop each value remaining in the stack and write ans[x] = 0
        /// Actually the array will have zeros in it already, so we can disregard remaining stack elements

        int[] ans = new int[temperatures.length];
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < temperatures.length; i++) {
            while (
                !stack.isEmpty() &&
                temperatures[stack.peek()] < temperatures[i]
            ) {
                int x = stack.pop();
                ans[x] = i - x;
            }

            stack.push(i);
        }

        return ans;
    }
}
