class Solution {
    public int minOperations(String[] logs) {
        // Could use a stack to model where we are in the file system
        // Push when we get an input like "d1/"
        // Pop when we get "../"
        // This approach would work, but since the dir that we're in doesn't really
        /// matter we can probably just use a counter for how deep we are.
        /// When we see something like "d1/" increment the counter.
        /// When we see "../" decrement the counter.

        // Problem doesn't say if we can do something like "../../d1"
        // I'm going to assume we don't have to deal with that for now.

        int depth = 0;
        for (String log : logs) {
            if (log.equals("../")) {
                // Don't decrement past 0
                if (depth > 0) {
                    depth--;
                }
            } else if (log.equals("./")) {
                // Do nothing
            } else {
                // Leaning into the problem definition here.
                // If we're in the else block, we know that we must be going into a sub dir.
                depth++;
            }
        }
        return depth;
    }
}