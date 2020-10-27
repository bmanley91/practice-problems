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
    // O(1) memory solution
    // First we use two pointers to detct if there's a cycle and to 
    // return the node in the cycle where the two pointers first meet.
    // Then we use two more pointers along with the intersection node to 
    // figure out where the start of the cycle is.
    public ListNode detectCycle(ListNode head) {
        ListNode intersection = findIntersection(head);
        
        // If there's no intersection, return null
        if (intersection == null) return null;
        
        // Use two pointers 
        // One starts at the head, the other at the intersection.
        // Because of math, if we advance them each once per cycle, 
        // they will intersect at the start of the cycle.
        ListNode startPointer = head;
        ListNode intersectionPointer = intersection;
        while (startPointer != intersectionPointer) {
            startPointer = startPointer.next;
            intersectionPointer = intersectionPointer.next;
        }
        
        return intersectionPointer;
    }
    
    // Helper function that uses two pointers to see if there's a cycle
    private ListNode findIntersection(ListNode head) {
        // Base case - If there's 0 or 1 elements in the list, there's no cycle
        if (head == null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) return slow;
        }
        
        return null;
    }
}