class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int sum = 0;
        int currentBottles = numBottles;
        int empties = 0;

        while(currentBottles != 0) {
            System.out.println("Bottles " + currentBottles);
            // Drink - Add to sum
            sum += currentBottles;
            empties += currentBottles;

            // Exchange
            currentBottles = empties / numExchange;
            empties = empties % numExchange;

        }

        return sum;
    }
}
