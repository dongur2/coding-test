import java.util.List; import java.util.ArrayList;
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
//연결 리스트의 헤드가 주어졌을 때, 모든 홀수 인덱스 노드를 끝으로 이동 
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode odd = head;         //홀수 인덱스 시작
        ListNode even = head.next;   //짝수 인덱스 시작
        ListNode evenHead = even;    //나중에 홀수 리스트 뒤에 붙일 <짝수 인덱스 리스트의 헤드>

        while (even != null && even.next != null) {
            odd.next = even.next;    //홀수 노드 연결
            odd = odd.next;

            even.next = odd.next;    //짝수 노드 연결
            even = even.next;
        }

        odd.next = evenHead;         //마지막에 짝수 리스트 연결
        return head;                 //원래 헤드 반환
    }
}