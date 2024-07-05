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
    public TreeNode balanceBST(TreeNode root) {
        // Just build a whole new tree!
        // Traverse the current tree in order
        /// and add node vals to a list
        /// Order: left, self, right
        // We should then have a list of node vals in sorted order
        // Use this to build a new tree:
        /// Basically do a binary search approach
        /// currentNode = mid
        /// currentNode.left = build(left half), currentNode.right = build(right half)

        List<Integer> inOrderValues = new ArrayList<Integer>();
        buildInOrderList(root, inOrderValues);
        return buildBST(inOrderValues, 0, inOrderValues.size() - 1);
    }

    private void buildInOrderList(TreeNode root, List<Integer> list) {
        // Base case - we've reached the end
        if (root == null) return;

        // Order: left, self, right
        buildInOrderList(root.left, list);
        list.add(root.val);
        buildInOrderList(root.right, list);
    }

    private TreeNode buildBST(List<Integer> values, int low, int high) {
        // Base case - we're done on this branch
        if (low > high) {
            return null;
        }

        // Current value is mid
        int mid = low + ((high - low) / 2);
        return  new TreeNode(
            values.get(mid),
            buildBST(values, low, mid - 1),
            buildBST(values, mid + 1, high)
        );
    }
}
