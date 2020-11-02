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
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode newHead = null;
        ListNode currentNode = head;
        ListNode nextNode = head.next;
        
        while (currentNode!= null) {
            nextNode = currentNode.next;
            currentNode.next = null;
            
            if (newHead == null) {
                newHead = currentNode;
            } else {
                newHead = insertNodeIntoList(newHead, currentNode);
            }
            currentNode = nextNode;
        }
        
        return newHead;
    }
    
    private ListNode insertNodeIntoList(ListNode head, ListNode nodeToInsert) {
        // Base Case - the current node is the new head.
        if (head.val >= nodeToInsert.val) {
            nodeToInsert.next = head;
            return nodeToInsert;
        } 
        
        ListNode currentNode = head;
        ListNode nextNode = head.next;
        
        while(nextNode != null) {
            if (nodeToInsert.val > currentNode.val && 
                nodeToInsert.val <= nextNode.val) {
                currentNode.next = nodeToInsert;
                nodeToInsert.next = nextNode;
                return head;
            }
            currentNode = nextNode;
            nextNode = currentNode.next;
        }
        
        currentNode.next = nodeToInsert;
        return head;
    }
}