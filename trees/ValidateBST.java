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
    // Traverse nodes in order.
    // Use a stack to keep track of the last node.
    // If at any point a node has a lower value than the previous, then this isn't a BST
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double previous = - Double.MAX_VALUE; // Using negative max double to handle an input containing min int.
        
        // We keep looping as long as we have nodes to process.
        while(!stack.isEmpty() || root != null) {
            // Keep adding the current node to the stack, then its left node.
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            
            // Get the last node added to the stack and make it the current node
            root = stack.pop();
            
            // Compare the current node's value to the previous value.
            // If the current is lower, then this isn't a BST.
            if (root.val <= previous) return false;
            
            // Set the current val to be the next previous val.
            previous = root.val;
            
            // We start out as far to the left as we can.
            // Then we compare to the root. Now we need to check the right side.
            root = root.right;
        }
        // If we get through the loop and every node's value was greater than the previous one
        // then this must be a BST.
        return true;
    }
}