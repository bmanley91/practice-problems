import java.util.ArrayList;
import java.util.List;

class Solution {
    public int findTheWinner(int n, int k) {
        // There's probably a way to do this with math...
        // Put numbers from 1 to n in a list
        // Keep track of positon starting at 1
        // Remove the element at position + k % playerList.size()
        // Once there is only one element left in the playerList return it

        // Edge case considerations
        // n will always be at least 1
        /// Our while loop will handle that by just not running if there's only one element in the player list

        List<Integer> playerList = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            playerList.add(i);
        }
        
        int position = 0;
        while(playerList.size() > 1) {
            position = (position - 1 + k) % playerList.size();
            playerList.remove(position);
        }

        // Return the only element left in the player list
        return playerList.get(0);
    }
}
