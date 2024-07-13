import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class Solution {
    private class Robot {
        int order;
        int position;
        int health;
        char direction;

        public Robot(int order, int position, int health, char direction) {
            this.order = order;
            this.position = position;
            this.health = health;
            this.direction = direction;
        }
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        // Create a list of robots, remembering their initial order
        List<Robot> robots = new ArrayList<Robot>();
        for (int i = 0; i < positions.length; i++) {
            robots.add(
                new Robot(
                    i,
                    positions[i],
                    healths[i],
                    directions.charAt(i)
                )
            );
        }
        
        // Sort robots by their starting positions in ascending order
        Collections.sort(robots, (a,b) -> Integer.compare(a.position, b.position));
        
        // Keep track of which Robots are still in play with a stack
        Stack<Robot> stack = new Stack<Robot>();
        for (Robot robot : robots) {
            // If Stack is empty, add current Robot to the stack
            if (stack.isEmpty()) {
                stack.add(robot);
            }
            // If the top of the stack is moving left, then any robot moving either right or left will never run into it
            // So we can just add to the stack
            else if (
                stack.peek().direction == 'L'
            ) {
                stack.add(robot);
            }
            // If the top of the stack and the current robot are both moving right, they won't run into each other
            // So just add to stack
            else if (robot.direction == 'R') {
                stack.add(robot);
            } 
            // Otherwise, this robot must be moving left and the top of the stack is moving right
            // the robot at the top of the stack will collide with the current robot so we have to resolve
            // the current robot and the stack
            else {
                while (
                    !stack.isEmpty() && 
                    stack.peek().direction == 'R'
                ) {
                    Robot stackBot = stack.pop();
                    // If both Robots have the same health, remove both 
                    // just break from the loop without adding to the stack
                    if (stackBot.health == robot.health) {
                        robot.health = 0;
                        break;
                    }
                    // If stackBot has higher health, decrement its health by 1 and return it to the stack
                    // Break from the loop
                    else if (stackBot.health > robot.health) {
                        stackBot.health--;
                        robot.health = 0;
                        stack.add(stackBot);
                        break;
                    } 
                    // If the current bot has higher health, decrement its health and continue iterating through the stack
                    else {
                        robot.health--;
                    }
                }

                // Once we finish resolving the current robot against the stack,
                /// add it to the stack if it has any health left
                if (robot.health > 0) {
                    stack.add(robot);
                }
            }
        }

        // Any robots remaining in the stack survived
        // Put them into a list and then sort it by their original order
        // We can probably optimize by using a list instead of a stack for the robots
        // Then we wouldn't have to do this extra list constructions
        List<Robot> survivors = new ArrayList<Robot>();
        while(!stack.isEmpty()) {
            survivors.add(stack.pop());
        }
        Collections.sort(survivors, (a,b) -> Integer.compare(a.order, b.order));

        List<Integer> output = new ArrayList<Integer>();
        for (Robot robot : survivors) {
            output.add(robot.health);
        }
        return output;
    }
}
