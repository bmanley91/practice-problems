class Solution {
    public double averageWaitingTime(int[][] customers) {
        // Keep a running clock number
        // Keep a running cumulative wait time
        // Process each customer in order since they are sorted by arrival time
        /// If the customer's arrival time is higher than the current clock
        //// Set clock = customer[arrival]
        //// Increment the clock by customer[time]
        //// Add clock - customer[arrival] to the cumulative wait time
        //// Return clock / customers.length

        double clock = 0.0;
        double cumulativeWait = 0.0;

        for (int[] customer : customers) {
            double arrival = (double) customer[0];
            double time = (double) customer[1];

            // Handle the case where the current customer arrived after the current clock time
            if (arrival > clock) {
                clock = arrival;
            }

            // Add the current customer's wait time to the clock
            clock += time;

            // Increment the cumulative wait time by the difference between the customer 
            /// arrived and when their order was ready
            cumulativeWait += clock - arrival;
        }
        return cumulativeWait / customers.length;
    }
}