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
    List<TreeNode> output = new ArrayList();
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> targets = new HashSet();
        for (int target : to_delete) {
            targets.add(target);
        }
        
        if (deleteNodesRecursive(root, targets) != null) {
            output.add(root);
        }
        
        return output;
    }

    private TreeNode deleteNodesRecursive(TreeNode root, Set<Integer> targets) {
        if (root == null) return null;

        root.left = deleteNodesRecursive(root.left, targets);
        root.right = deleteNodesRecursive(root.right, targets);

        if (targets.contains(root.val)) {
            if (root.left != null) {
                output.add(root.left);
            }
            if (root.right != null) {
                output.add(root.right);
            }
            return null;
        }

        return root;
    }
}
