import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {
    public static void main(String[] args) {
        int[] input1 = {70, 70, 70, 75};
        int[] result1 = findDaysUntilWarmerBeter(input1);
        System.out.println(Arrays.toString(result1));
    }

    // Better approach
    // Store indexes in the stack rather than numbers
    private static int[] findDaysUntilWarmerBetter(int[] days) {
        int[] output = new int[days.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < days.length; i++) {
            while (!stack.isEmpty() && days[i] > days[stack.peek()]) {
                int index = stack.pop();
                output[index] = i - index;
            }
            stack.push(i);
            System.out.println("Stack after i=" + i + " " + stack.toString());
        }

        return output;
    }


    private static int[] findDaysUntilWarmer(int[] days) {
        // Work from right to left
        // Add each temp to a stack as we iterate
        // pop the stack until the top is greater than the current temp
        // count number of pops
        // If we empty the stack, then the result for this position is 0

        int[] output = new int[days.length];
        Stack<Integer> temperatureStack = new Stack<Integer>();
        
        for (int i = output.length - 1; i >= 0; i--) {
            System.out.println("Current temp is " + days[i]);
            int counter = 0;
            // Make a copy of the stack so we don't need to repopulate it.
            Stack<Integer> currentStack = (Stack<Integer>) temperatureStack.clone();
            while(
                !currentStack.isEmpty()
            ) {
                counter++;
                if (currentStack.pop() > days[i]) {
                    output[i] = counter;
                }
            }

            // Array is full of 0 by default.
            // If we make it through the whole stack, just don't write to the output for this day.
            
            System.out.println("Output for day " + i + " is " + output[i] + "\n");
            temperatureStack.push(days[i]);
            System.out.println("Stack after position " + i + " is " + temperatureStack.toString());
        }

        return output;
    }
}
