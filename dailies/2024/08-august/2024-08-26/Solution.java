import java.util.ArrayList;
import java.util.List;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};


class Solution {
    public List<Integer> postorder(Node root) {
        // Pass an initially empty list into our recursive traversal function
        List<Integer> output = new ArrayList<Integer>();
        visit(root, output);
        return output;
    }

    private void visit(Node node, List<Integer> visited) {
        // Base case - We've reached the end of a branch
        if (node == null) return;

        // Each node can have n children, loop through them and visit them.
        for (Node child : node.children) {
            visit(child, visited);
        }

        // Since we want to post-order traverse, add the current node's value to the output 
        /// after we've visited each child
        visited.add(node.val);
    }
}