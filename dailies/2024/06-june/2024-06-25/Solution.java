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
    int sum = 0;
    public TreeNode bstToGst(TreeNode root) {
        // Since this is bst, greater nodes are on the right
        // Recursively go to the highest node
        // Keep a running sum
        // Add each node's val to the sum
        // Then replace the sum with the node's new val
        // Go right, then self, then left

        // Edge case considerations
        // Node values will always be unique
        // Tree can be empty
        visitNode(root);
        return root;
    }
    
    private void visitNode(TreeNode root) {
        // Base case - if the node is null there's nothing to do
        if (root == null) return;
        
        // Visit right
        visitNode(root.right);

        // Then self
        root.val += sum;
        sum = root.val;

        // Then left
        visitNode(root.left);
    }
}
