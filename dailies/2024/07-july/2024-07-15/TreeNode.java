import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {
        // Since values are unique, we can create a map of values to their corresponding TreeNodes
        // Then we can process the input in order.
        // For each description:
        /// Check if the parent val is already a key in the map
        //// If so, we're going to update this node
        //// If not, create a new with the value and put it in the map
        /// Then check if the child is already in the map
        //// If it is, get the child node from the map and 
        //// If it is not, create a new node, put it in the map
        //// Either way set parent.left or right based on the isLeft value to the child node
        // Figuring out the root val is a bit awkward.
        /// We can keep track of each child val that we've seen
        /// Then iterate over the parent vals at the end and return the only one which is _not_ a child.

        // Edge Case considerations 
        // There will be at least one description provided so we don't need to worry about empty input.
        
        Map<Integer, TreeNode> nodeMap = new HashMap<Integer, TreeNode>();
        Set<Integer> nodesWithParent = new HashSet<Integer>();

        for (int[] description : descriptions) {
            int parent = description[0];
            int child = description[1];
            boolean isLeft = description[2] == 1;
            nodesWithParent.add(child);

            // Get or create new parent node
            TreeNode parentNode;
            if (nodeMap.containsKey(parent)) {
                parentNode = nodeMap.get(parent);
            } else {
                parentNode = new TreeNode(parent);
                nodeMap.put(parent, parentNode);
            }

            // Get or create new child node
            TreeNode childNode;
            if (nodeMap.containsKey(child)) {
                childNode = nodeMap.get(child);
            } else {
                childNode = new TreeNode(child);
                nodeMap.put(child, childNode);
            }

            if (isLeft) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
        }

        for (int parentVal : nodeMap.keySet()) {
            if (!nodesWithParent.contains(parentVal)) {
                return nodeMap.get(parentVal);
            }
        }
        return null;
    }
}

public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }