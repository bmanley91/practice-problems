/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    // Non O(1) memory solution:
    // Use a Set to keep track of the nodes we've visited
    // Traverse the list and for each node, see if it's in the set of visited nodes.
    // If so return the node 
    // If we reach the tail node, return null?
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> visitedNodes = new HashSet<>();
        
        ListNode currentNode = head;
        while (currentNode != null) {
            // If we see the current node in the Set of visited nodes, then return it.
            if (visitedNodes.contains(currentNode)) return currentNode;
            
            // If we haven't visited this node before, 
            // then add the current node to the set and move on
            visitedNodes.add(currentNode);
            currentNode = currentNode.next;
        }
        
        // If we complete the loop, then we must have reached the tail.
        // This means there's no cycle.
        return null;
    }
}