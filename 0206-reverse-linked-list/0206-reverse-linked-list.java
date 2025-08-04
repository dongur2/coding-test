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
 //주어진 연결 리스트를 뒤집기 
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode point = null; //가리키는 노드

        while(head != null) {
            ListNode temp = head.next; //원래 노드가 가리키던 노드를 잠깐 저장 
            head.next = point; //노드가 가리키는 노드를 수정 
            point = head; //다음 노드가 가리킬 노드 업데이트 
            
            //가리키는 노드가 없으면 끝 
            if(temp == null) return head;
            else head = temp; //노드 이동 
        }

        return head;
    }
}