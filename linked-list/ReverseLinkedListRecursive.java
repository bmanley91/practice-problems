/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // This recursive approach follows a DFS-like pattern
    // First we go all the way down to the end of the list, 
    // keeping track of the current and previous node as we go.
    // When we get to the end of the list, we return that node because it will be the new head.
    // We keep track of the new head as we backtrack up the callstack.
    // When backtracking, we rewire the current node's next pointer to the previous node.
    // At the end we return the node that the recursive helper has been returning 
    // since it reached the end of the original list.
    public ListNode reverseList(ListNode head) {
        return reverseListRecursiveHelper(head, null);
    }
    
    private ListNode reverseListRecursiveHelper(ListNode current, ListNode previous) {
        if (current == null) return previous;
        
        ListNode newHead = reverseListRecursiveHelper(current.next, current);
        
        current.next = previous;
        
        return newHead;
    }
}