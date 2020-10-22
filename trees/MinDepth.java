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
    public int minDepth(TreeNode root) {
        // Base Case - If there's no tree return 0.
        if (root == null) return 0;
        
        // Do a BFS
        // Keep track of depth as we go
        // Once we find a node with no children, return the current depth.
        Queue<TreeNodeWrapper> queue = new LinkedList<>();
        queue.add(new TreeNodeWrapper(root, 1));
        
        while(!queue.isEmpty()) {
            TreeNodeWrapper current = queue.poll();
            if (current.node.left == null && current.node.right == null) {
                return current.depth;
            }
            
            int nextDepth = current.depth + 1;
            
            if (current.node.right != null) {
                queue.add(new TreeNodeWrapper(current.node.right, nextDepth));
            }
            
            if (current.node.left != null) {
                queue.add(new TreeNodeWrapper(current.node.left, nextDepth));
            }
        }
        
        // Should never happen.
        return -1;
    }
    
    // Use a wrapper class to keep track of depth alongside a node as we go.
    class TreeNodeWrapper {
        TreeNode node;
        int depth;
        
        TreeNodeWrapper(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}