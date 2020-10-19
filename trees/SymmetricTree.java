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
    // A tree is symmetric if the left subtree is a mirror of the right subtree
    // Trees are mirrors of eachother if they have the same root 
    // and the right subtree of each tree is a mirror reflection of the left subtree of the other tree.
    public boolean isSymmetric(TreeNode root) {
        // Base Case - No tree
        if (root == null) return true;
        
        return treesAreMirrors(root.left, root.right);
    }
    
    private boolean treesAreMirrors(TreeNode tree1, TreeNode tree2) {
        // Base Case - if the trees are empty then they're mirrors
        if (tree1 == null && tree2 == null) return true;
        
        // Base Case - if one of the trees is null, but the other isn't, then they're not mirrors
        if (tree1 == null || tree2 == null) return false;
        
        return (tree1.val == tree2.val) && // If they have the same root
            treesAreMirrors(tree1.right, tree2.left) && // and the sub trees are mirrors of each other.
            treesAreMirrors(tree1.left, tree2.right);
    }
}