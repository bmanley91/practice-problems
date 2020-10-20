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
    // Do a breadth first traversal
    // Keep track of level as we go, starting at 0
    // Level determines which direction we want to traverse in
    // Even: left to right
    // Odd: right to left
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // Base case - if the input is empty just return our empty output.
        if (root == null) return new ArrayList<List<Integer>>();
        
        // Initialize Output
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        output.add(new ArrayList<Integer>());
        
        Queue<TreeNodeWrapper> queue = new LinkedList<>();
        
        // Enqueue the root node at level 0.
        int currentLevel = 0;
        queue.add(new TreeNodeWrapper(root, currentLevel));
        
        while(!queue.isEmpty()) {
            TreeNodeWrapper treeNodeWrapper = queue.poll();
            
            // If we've moved to a new level, create a new list
            // Otherwise get the existing one.
            List<Integer> currentLevelList;
            if (treeNodeWrapper.level != currentLevel) {
                currentLevelList = new ArrayList<Integer>();
                currentLevel = treeNodeWrapper.level;
            } else {
                currentLevelList = output.get(treeNodeWrapper.level);
            }
            
            // Add the current value to the list then add the list for the current level to the output.
            currentLevelList.add(treeNodeWrapper.node.val);
            
            // If a list doesn't exist at the current level add one.
            // If it does exist, overwrite it.
            if (output.size() <= treeNodeWrapper.level) {
                output.add(currentLevelList);
            } else {
                output.set(treeNodeWrapper.level, currentLevelList);    
            }
            
            // Figure out what the next level is.
            // If it's even, add left then right to the queue.
            // If it's odd, add right then left to the queue.
            int nextLevel = treeNodeWrapper.level + 1;
            if (nextLevel % 2 == 0) {
                addNodeToQueueIfNotNull(treeNodeWrapper.node.left, nextLevel, queue);
                addNodeToQueueIfNotNull(treeNodeWrapper.node.right, nextLevel, queue);
            } else {
                addNodeToQueueIfNotNull(treeNodeWrapper.node.right, nextLevel, queue);
                addNodeToQueueIfNotNull(treeNodeWrapper.node.left, nextLevel, queue);
            }
        }
        
        return output;
    }
    
    private void addNodeToQueueIfNotNull(TreeNode node, int level, Queue<TreeNodeWrapper> queue) {
        if (node != null) {
            System.out.println("Adding " + node.val + " to queue for level " + level);
            queue.add(new TreeNodeWrapper(node, level));
        }
    }
    
    // Create a wrapper class so that we can keep track of the 
    // level along with the node in the queue used for BFT
    class TreeNodeWrapper {
        TreeNode node;
        int level;
        
        public TreeNodeWrapper(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
}