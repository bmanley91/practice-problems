class Solution {
    public int findCenter(int[][] edges) {
        // This seems too easy?
        // Just iterate through and see what number is in each edge
        // If we know that the input is a star graph, we can just look 
        /// at the first two edges and see which number is in both

        // Edge case considerations
        // There will be at least three stars
        /// Which means there will be at least 2 edges

        if (edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1]) {
            return edges[0][0];
        } else {
            return edges[0][1];
        }
    }
}
