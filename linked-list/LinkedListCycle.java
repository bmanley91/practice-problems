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
    // Use two pointers, fast and slow
    // Each cycle, slow advances by one node and fast advances by two
    // If at any point slow == fast or slow.next == fast, return true
    // If fast reaches the tail, return false
    public boolean hasCycle(ListNode head) {
        // Base case - If head or head.next is null, there's no cycle
        if (head == null || head.next == null) return false;
        
        // Start fast two positions ahead of slow so that we don't prematurely return true
        ListNode slow = head;
        ListNode fast = head.next.next;
        
        while (fast != null && fast.next != null) {
            // Check to see if we've cycled. If so, short circuit and return.
            if (slow == fast || slow == fast.next) return true;
            
            slow = slow.next;
            fast = fast.next.next;   
        }
        
        return false;
    }
}