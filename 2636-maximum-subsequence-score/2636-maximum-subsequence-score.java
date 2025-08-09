import java.util.List; import java.util.ArrayList;
import java.util.Queue; import java.util.PriorityQueue;
/*
    숫자배열 nums1, nums2 (둘의 길이는 n으로 같음)
    양의 정수 k
    
    k개 인덱스를 골라야 함
    - nums1에서 선택된 요소들의 합에 nums2에서 선택된 요소들 중 최소값과 곱한 값이 점수가 된다.

    가능한 최대 점수를 리턴 
    - 인덱스는 띄엄띄엄 고를 수 있음
 */
class Solution {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        long max = 0L;
        
        int n = nums1.length;

        //nums2와 nums1 같은 인덱스에 해당하는 값끼리 묶어서 정렬: nums2 내림차순, 같으면 nums1 큰 순서 
        List<Entry> list = new ArrayList<>();
        for(int i=0; i<n; i++) { //O(n)
            list.add(new Entry(nums1[i], nums2[i]));
        }
 
        Collections.sort(list, (a,b) -> { //O(nlogn)
            if(a.n2 != b.n2) return b.n2 - a.n2;
            return b.n1 - a.n1;
        });

        long sum = 0L;

        Queue<Integer> pq = new PriorityQueue<>(); //최소 힙 - 가장 작은 n1을 바로 빼기 위한 목적 

        //o(n) - o(nlogn)
        for(int i=0; i<n; i++) {
            Entry curr = list.get(i);
            pq.add(curr.n1); //일단 n1 넣고  O(logn)
            sum += curr.n1; //nums1 합에 추가 

            //이미 k개를 초과했으면 가장 작은 n1 값을 삭제 
            if(pq.size() > k) sum -= pq.poll(); //O(logn)
            //k개를 만족하면 정답 업데이트
            if(pq.size() == k) max = Math.max(max, sum * curr.n2);
        }

        return max;
    }

    class Entry {
        int n1, n2;
        public Entry(int n1, int n2) {
            this.n1=n1; this.n2=n2;
        }
    }
}