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
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        // Recursively traverse the list to find all duplicates
        // Use a map to keep track of occurrances
        // As we move back up the call stack, if a node's val has occurrances > 1
        /// Then return the next node
        // If it doesn't then update curr.next to the response from the recursive removal

        // This approach only beats 5% for some reason.

        return deleteDuplicatesRecursive(head, new HashMap<Integer, Integer>());
    }

    private ListNode deleteDuplicatesRecursive(ListNode curr, Map<Integer, Integer> occurrenceMap) {
        if (curr == null) {
            return null;
        }

        // Update the number of occurances of this value
        if (occurrenceMap.containsKey(curr.val)) {
            occurrenceMap.put(curr.val, occurrenceMap.get(curr.val) + 1);
        } else {
            occurrenceMap.put(curr.val, 1);
        }

        // Recurse first so that we can build out the occurrance map
        ListNode newNext = deleteDuplicatesRecursive(curr.next, occurrenceMap);

        if (occurrenceMap.get(curr.val) > 1) {
            return newNext;
        } else {
            curr.next = newNext;
            return curr;
        }
    }
}