/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base Case - We've reached the end before finding one of the targets, return null
        if (root == null) return null;

        // If we've found one of the targets, return this node
        if (root == p || root == q) {
            return root;
        }

        // Search Right
        TreeNode left = lowestCommonAncestor(root.left, p, q);

        // Search Left
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If one side is null return the other.
        if (left == null) return right;
        if (right == null) return left;

        // Otherwise return the current node
        return root;
    }
}

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
