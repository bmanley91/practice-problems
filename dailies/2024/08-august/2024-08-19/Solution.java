class Solution {
    public int minSteps(int n) {
        // Keep track of how many steps we've taken, how many chars we have, 
        // and the length of the string in the clipboard
        // Check the diff between the number of chars and n
        // If this is divisible by the length of the clipboard
        /// Then check stepsSoFar + ((n - length)/clipboard) against a running minimum
        // Then add the clipboard's length to the notepad 
        /// and set clipboard length to the notepad's current length
        
        int notepad = 1;
        int stepsSoFar = 0;
        int clipboard = 1;
        int minSteps = Integer.MAX_VALUE;

        while (notepad < n) {
            System.out.println("notepad " + notepad);
            System.out.println("clipboard " + clipboard);
            System.out.println("soFar " + stepsSoFar);
            int remaining = n - notepad;
            if (
                // Don't try to divide by 0
                clipboard != 0 &&
                // Check if remaining number of chars is divisible by the 
                // length of the string in the clipboard
                remaining % clipboard == 0
            ) {
                System.out.println("Remainder " + remaining + " is divisible by " + notepad);
                minSteps = Math.min(
                    minSteps,
                    stepsSoFar + (remaining / clipboard)
                );
            }
            // Now we paste the current clipboard to the note pad
            // and copy the new notepad
            // This is two operations, so increment the step counter by two
            notepad += clipboard;
            clipboard = notepad;
            stepsSoFar +=2;
            System.out.println("min so far: " + minSteps);
            System.out.println("\n-----\n");
        }

        return minSteps == Integer.MAX_VALUE ? 0 : minSteps;
    }
}