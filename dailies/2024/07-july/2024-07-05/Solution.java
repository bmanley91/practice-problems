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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] output = new int[2];
        output[0] = -1;
        output[1] = -1;
        if (head == null || head.next == null || head.next.next == null) {
            return output;
        }

        // Keep track of critical points and minDistance as we go
        List<Integer> criticalPoints = new ArrayList();
        int minDistance = Integer.MAX_VALUE;

        // Start the loop looking at the second element
        ListNode prev = head;
        ListNode curr = head.next;
        ListNode next = head.next.next;
        int currentPosition = 1;
        // End the loop once the window passes the end of the list
        while (next != null) {
            if (isCriticalPoint(prev, curr, next)) {
                // Check this point against the most recent critical point
                // We can only compare if this is the second critical point or later
                if (criticalPoints.size() > 0) {
                    minDistance = Integer.min(
                        minDistance,
                        currentPosition - criticalPoints.get(criticalPoints.size() - 1)
                    );
                }
                // Regardless of the above, we want to add the position to the end of the list
                // We could use a stack here instead, but it would make the max distance logic more awkward
                criticalPoints.add(currentPosition);
            }

            // Advance the window
            prev = prev.next;
            curr = curr.next;
            next = next.next;
            currentPosition++;
        }

        // If we had less than two critical points, then return the default value
        // Otherwise, return our min-max distances
        if (criticalPoints.size() > 1) {
            output[0] = minDistance;
            output[1] = criticalPoints.get(criticalPoints.size()-1) - criticalPoints.get(0);
        }
        return output;
    }

    private boolean isCriticalPoint(ListNode prev, ListNode curr, ListNode next) {
        return
            (curr.val < prev.val && curr.val < next.val) ||
            (curr.val > prev.val && curr.val > next.val);
    }
}