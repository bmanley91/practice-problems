import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        // Optimization, use a List instead of a Stack to remove extra iteration later.
        List<Robot> pastRobots = new ArrayList<Robot>();
        for (Robot robot : robots) {
            // If Stack is empty, add current Robot to the stack
            if (pastRobots.size() == 0) {
                pastRobots.add(robot);
            }
            // If the top of the stack is moving left, then any robot moving either right or left will never run into it
            // So we can just add to the stack
            else if (
                pastRobots.get(pastRobots.size() - 1).direction == 'L'
            ) {
                pastRobots.add(robot);
            }
            // If the top of the stack and the current robot are both moving right, they won't run into each other
            // So just add to stack
            else if (robot.direction == 'R') {
                pastRobots.add(robot);
            } 
            // Otherwise, this robot must be moving left and the top of the stack is moving right
            // the robot at the top of the stack will collide with the current robot so we have to resolve
            // the current robot and the stack
            else {
                while (
                    pastRobots.size() != 0 && 
                    pastRobots.get(pastRobots.size() - 1).direction == 'R'
                ) {
                    Robot stackBot = pastRobots.remove(pastRobots.size() - 1);
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
                        pastRobots.add(stackBot);
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
                    pastRobots.add(robot);
                }
            }
        }

        Collections.sort(pastRobots, (a,b) -> Integer.compare(a.order, b.order));

        List<Integer> output = new ArrayList<Integer>();
        for (Robot robot : pastRobots) {
            output.add(robot.health);
        }
        return output;
    }
}
