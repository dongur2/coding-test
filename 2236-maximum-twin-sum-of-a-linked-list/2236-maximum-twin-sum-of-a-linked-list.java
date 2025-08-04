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
//길이 n(짝수)인 연결 리스트에서, 0<=i<=(n/2)-1일 경우 i번째 노드는 (n-1-i)번째 노드와 쌍둥이 노드
//이 때 가장 큰 쌍둥이 합
class Solution {
    public int pairSum(ListNode head) {
        //길이 계산
        int n = 0;
        ListNode curr = head;
        while(curr != null) {
            curr = curr.next;
            n++;
        }

        //앞 값 저장 
        int[] pre = new int[n/2];
        curr = head;
        for(int i=0; i<n/2; i++) {
            pre[i] = curr.val;
            curr = curr.next;
        }

        //저장한 값에 뒷 값 더해서 합 확인 - max 구하기 
        int max = Integer.MIN_VALUE;
        for(int i=1; i<=n/2; i++) {
            int sum = pre[n/2-i]+curr.val;
            max = Math.max(max, sum);
            curr = curr.next;
        }

        return max;
    }
}