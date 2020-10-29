/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // Use a recursive function that takes in the two current nodes and
    // plus a new node that'll be part of the output list.
    // Add the values of the two nodes, 
    // If it's less than 10, put it in the output node and recurse using the current nodes' nexts
    // and a new node with val 0 for the output node's next.
    // If the sum is >= 10, do pretty much the same thing, but subtract 10 from the sum 
    // and for the next output node, start with a val of 1
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode outputHead = new ListNode(0);
        addNodesRecursive(l1, l2, null, outputHead);
        return outputHead;
    }
    
    // Recursive helper functions to add two nodes and store their sum in an output node.
    private void addNodesRecursive(ListNode l1, ListNode l2, ListNode outputPrev, ListNode outputNode) {
        // Once both l1 and l2 are null, we're done!
        if (l1 == null && l2 == null) {
            // If we have a trailing 0, get rid of it.
            if (outputNode.val == 0) {
                outputPrev.next = null;
            }
            return;
        }
        
        ListNode outputNext = new ListNode(0);
        
        int currentSum = addNodes(l1, l2, outputNode);
        
        if (currentSum >= 10) {
            currentSum -= 10;
            outputNext.val = 1;
        }
        
        outputNode.val = currentSum;
        outputNode.next = outputNext;
        
        if (l1 == null) {
            addNodesRecursive(null, l2.next, outputNode, outputNext);
        } else if (l2 == null) {
            addNodesRecursive(l1.next, null, outputNode, outputNext);
        } else {
            addNodesRecursive(l1.next, l2.next, outputNode, outputNext);
        }
    }
    
    private int addNodes(ListNode l1, ListNode l2, ListNode l3) {
        int nullSafe1 = l1 == null ? 0 : l1.val;
        int nullSafe2 = l2 == null ? 0 : l2.val;
        int nullSafe3 = l3 == null ? 0 : l3.val;
        
        return nullSafe1 + nullSafe2 + nullSafe3;
    }
}