class Solution {
    public int findMinArrowShots(int[][] points) {
        // Find overlaps to figure out optimal arrow firing positions
        // Sort the balloons by end coordinate
        // Fire from the end coordinate of sorted[0]
        // Then iterate until a start coordinate is higher than the position we fired from
        // Fire from that balloon's end position
        // Repeat until we hit the end of the balloons

        Arrays.sort(points, (a, b) -> Integer.compare(a[1],b1));

        // We always need to fire at least once
        int countFired = 1;
        int firingPosition = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > firingPosition) {
                countFired++;
                firingPosition = points[i][1];
            }
        }
        return countFired;
    }
}
