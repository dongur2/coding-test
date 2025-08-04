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
 //중간 노드 삭제 
class Solution {
    public ListNode deleteMiddle(ListNode head) {
        //리스트 길이가 1일 경우 즉시 삭제 후 리턴 
        if (head == null || head.next == null) return null;

        //전체 길이 카운트 
        int len = 0; 
        ListNode curr = head;
        while(curr != null) {
            curr = curr.next; //헤드 이동 
            len++;
        }

        //중간 인덱스 
        int mid = len / 2;

        //중간 직전 인덱스 노드까지 이동
        curr = head;
        for(int i=0; i<mid-1; i++) {
            curr = curr.next;
        }

        //중간 노드 분리(삭제)
        curr.next = curr.next.next;

        return head;
    }
}