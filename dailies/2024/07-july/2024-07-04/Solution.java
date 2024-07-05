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


 // This solution was somehow slower than 95%?

class Solution {
    public ListNode mergeNodes(ListNode head) {
        // First and last nodes are guaranteed to be 0
        // Iterate through the list keeping a running sum
        // Every time we hit a zero create a new node with the running sum as the value and next =
        /// Recursive call that repeats the running sum process
        // Since the last number is guaranteed to be a zero, we don't have to worry about
        /// a leftover case

        // Edge case considerations
        // No mention of empty list so we must assume it's possible
        /// Do a null check at the beginning
        // "No two consecutive nodes with Node.val == 0"
        /// So we won't have a case where the input list is [0,0]
        // From the description, it seems like [0] as an input is valid
        /// this should be handled by our recursive function

        if (head == null) return null;

        // Since first node is guaranteed to be 0
        /// Start with node.next
        return sumBetweenZeros(head.next, 0);
    }

    private ListNode sumBetweenZeros(ListNode node, int sumSoFar) {
        // If node is null, we've reached the end of the list
        // Since we're guaranteed to end with a 0, we don't 
        /// need to worry about adding a final node
        if (node == null) return null;

        // Two possibilities - val is 0 or not
        if (node.val == 0) {
            return new ListNode(
                sumSoFar,
                sumBetweenZeros(node.next, 0)
            );
        } else {
            return sumBetweenZeros(node.next, sumSoFar + node.val);
        }
    }
}
