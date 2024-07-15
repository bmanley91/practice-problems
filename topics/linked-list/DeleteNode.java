/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class DeleteNode {
    // This approach doesn't "delete" the node per se.
    // It modifies each node from the target to the end of the list to have the next node's value.
    // Then the last node is removed from the list.
    // The end result is a list that _looks_ like a node was deleted from it.
    public void deleteNode(ListNode node) {
        // Base Case - if the target node is null exit early.
        if (node == null) return;
        
        
        // Start at the node to delete and changes its value to that of the node in front of it.
        // Iterate through the list, repeating this process until node.next.next is null.
        // Once that happens replace the current node's value with the next node's value, 
        // and set the current node's next to null.
        
        ListNode currentNode = node;
        while(currentNode.next.next != null) {
            currentNode.val = currentNode.next.val;
            currentNode = currentNode.next;
        }
        
        currentNode.val = currentNode.next.val;
        currentNode.next = null;
    }
}