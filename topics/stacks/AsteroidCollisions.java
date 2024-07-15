class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        // Base Case - if there's zero or one asteroids, return the input
        if (asteroids.length <= 1) return asteroids;
        
        // Use a stack to keep track of asteroids
        Stack<Integer> stack = new Stack<Integer>();
        
        // Iterate through each asteroid
        int currentAsteroidIndex = 0;
        while (currentAsteroidIndex < asteroids.length) {
            // If the stack is empty, put the current asteroid on the stack
            if (stack.isEmpty()) {
                stack.push(asteroids[currentAsteroidIndex]);
                currentAsteroidIndex++;
                
            } else {
                int currentAsteroid = asteroids[currentAsteroidIndex];
                int previousAsteroid = stack.peek();
                
                // If the current asteroid current and previous asteroids are headed in the same direction, 
                // or if the previous asteroid is headed to the left and the current asteroid to the right
                // then we just add the current asteroid to the stack and move on.
                if (!areHeadedTowardEachOther(previousAsteroid, currentAsteroid)) {
                    stack.push(currentAsteroid);
                    currentAsteroidIndex++;
                } else {
                    // Check to see if the top asteroid has a higher abs value than the current asteroid
                    int currentAsteroidSize = Math.abs(currentAsteroid);
                    int previousAsteroidSize = Math.abs(previousAsteroid);
                    
                    if (previousAsteroidSize > currentAsteroidSize) {
                        // If it does, discard the current asteroid
                        currentAsteroidIndex++;
                        
                    } else if (previousAsteroidSize < currentAsteroidSize) {
                        // If it doesn't, pop the top asteroid 
                        stack.pop();
                        
                        // We don't increase the currentAsteroidIndex here so that on the next loop iteration, we'll continue to look at this asteroid so that we can compare it to the next asteroid in the stack.
                        
                    } else {
                        // If they're the same, pop the top element of the stacka and move to the next asteroid
                        stack.pop();
                        currentAsteroidIndex++;
                    }
                }
            }
        }
        
        // Construct output array from stack.
        // Array is populated in reverse order since that's how elements are stored in the stack.
        int[] output = new int[stack.size()];
        int i = stack.size() - 1;
        while (!stack.isEmpty()) {
            output[i] = stack.pop();
            i--;
        }

        return output;
    }
    
    private boolean areHeadedTowardEachOther(int prev, int next) {
        return prev > 0 && next < 0;
    }
}