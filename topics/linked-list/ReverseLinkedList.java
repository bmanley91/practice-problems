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
    // Iterate through nodes and put them into a stack. 
    // When we hit the end of the list, pop nodes off of the stack, 
    // setting the previous node.next = popped node
    public ListNode reverseList(ListNode head) {
        // Base Case - if the list is empty, return null
        if (head == null) return null;
        
        Stack<ListNode> stack = new Stack<ListNode>();
        
        ListNode currentNode = head;
        while (currentNode != null) {
            stack.push(currentNode);
            currentNode = currentNode.next;
        }
        
        ListNode reversedHead = stack.pop();
        currentNode = reversedHead;
        while (!stack.isEmpty()) {
            ListNode nextNode = stack.pop();
            currentNode.next = nextNode;
            currentNode = nextNode;
        }
        currentNode.next = null;
        
        return reversedHead;
    }
}