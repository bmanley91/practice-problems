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
    public ListNode plusOne(ListNode head) {
        int carry = recursivelyAddOne(head);
        
        if (carry > 0) {
            ListNode newNode = new ListNode(carry, head);
            head = newNode;
        }
        
        return head;
    }
    
    private int recursivelyAddOne(ListNode node) {
        if (node == null) {
            return 1;
        }
        
        int newVal = node.val + recursivelyAddOne(node.next);
        
        if (newVal >= 10) {
            node.val = newVal - 10;
            return 1;
        } else {
            node.val = newVal;
            return 0;
        }
    }
}