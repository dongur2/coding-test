import java.util.Queue; import java.util.PriorityQueue;
/*
    숫자 배열 nums, 숫자 k
    배열에서 k번째로 큰 요소 리턴 
    정렬 없이 구하기 
 */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> pq = new PriorityQueue<>((a,b) -> {return b-a;});
        for(int n:nums) {
            pq.offer(n);
        }

        int idx = 1;
        while(!pq.isEmpty()) {
            int curr = pq.poll();
            if(idx++ == k) return curr;
        }
        
        return 0;
    }
}