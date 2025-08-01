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
//연결리스트의 헤드가 주어지면, 거꾸로 뒤집은 연결리스트를 리턴 
class Solution {
    public ListNode reverseList(ListNode head) {
        //가지고 이동할 노드. 
        ListNode newHead = null;

        //각 노드가 가리키는 노드를 뒤집기 
        while(head != null) {
            ListNode next = head.next; //다음 노드
            head.next = newHead; //헤드가 가리키는 노드를 업데이트 (첫 노드는 null(거꾸로 뒤집어야 하므로))
            newHead = head; //현재 노드: 헤드  
            head = next;  //가리키던 노드로 이동 
        }

        //새로운 헤드 리턴 
        return newHead;
    }
}