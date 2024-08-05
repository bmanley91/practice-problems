public class SumOfSquares {
    public static void main(String[] args) {
        int input1 = 5;
        int output1 = 55;
        System.out.println("Test 1: " + (justUseMath(input1) == output1));
        System.out.println("Test 1: " + (squares(input1) == output1));

        int input2 = 10;
        int output2 = 385;
        System.out.println("Test 2: " + (justUseMath(input2) == output2));
        System.out.println("Test 2: " + (squares(input2) == output2));

        int input3 = 25;
        int output3 = 5525;
        System.out.println("Test 3: " + (justUseMath(input3) == output3));
        System.out.println("Test 3: " + (squares(input3) == output3));

        int input4 = 100;
        int output4 = 338350;
        System.out.println("Test 4: " + (justUseMath(input4) == output4));
        System.out.println("Test 4: " + (squares(input4) == output4));

        int input5 = 0;
        int output5 = 0;
        System.out.println("Test 5: " + (justUseMath(input5) == output5));
        System.out.println("Test 5: " + (squares(input5) == output5));
    }

    private static long squares(int n) {
        long sum = 0l;

        // Start loop at 0 to handle n = 0 case
        for (long i = 0; i <= n; i++) {

            sum += i * i;
        }

        return sum;
    }

    // Apparently this is a thing
    private static long justUseMath(int n) {
        return n * (n + 1) * (2 * n + 1) / 6;
    }
}
