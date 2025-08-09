/*
    숫자배열 costs
    costs[i] = i번째 직원의 급여

    숫자 k, candidates
    다음 규칙에 따라 k명을 고용:
    
    - 세션 당 한 명이 근무
    - 각 세션에서 앞에서 candidates명, 뒤에서 candidates명을 추려서 그 중에 가장 낮은 급여를 원하는 직원을 선택, 동일하면 작은 인덱스
    - 직원이 후보보다 적으면, 남은 사람 중에 가장 낮은 급여 선택, 동일하면 작은 인덱스
    - 직원은 한 번만 근무 가능

    k명을 고용하는 데 드는 총비용 리턴 
 */
import java.util.Queue; import java.util.PriorityQueue;
class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        long cost = 0;

        /* 
            최소힙 [인덱스, 급여: 급여 적은순, 인덱스 작은순 
        */
        //왼쪽 후보군
        Queue<int[]> leftQ = new PriorityQueue<>((a,b) -> {
            if(a[1] != b[1]) return a[1] - b[1];
            return a[0] - b[0];
        }); 
        //오른쪽 후보군
        Queue<int[]> rightQ = new PriorityQueue<>((a,b) -> {
            if(a[1] != b[1]) return a[1] - b[1];
            return a[0] - b[0];
        });

        //후보 등록 
        int left = 0, right = costs.length-1;
        for(int i=0; i < candidates && left <= right; i++) {
            leftQ.offer(new int[]{left, costs[left++]});
        }
        for(int i=0; i < candidates && left <= right; i++) {
            rightQ.offer(new int[]{right, costs[right--]});
        }

        //고용
        for(int h=0; h<k; h++) {
            //후보군에서 최저 급여 

            //왼쪽 후보군에서 고용
            if(rightQ.isEmpty() || (!leftQ.isEmpty() && leftQ.peek()[1] <= rightQ.peek()[1])) {
                int[] worker = leftQ.poll();
                cost += worker[1];

                if(left <= right) leftQ.offer(new int[]{left, costs[left++]});
            
            //오른쪽 후보군에서 고용
            } else {
                int[] worker = rightQ.poll();
                cost += worker[1];

                if(left <= right) rightQ.offer(new int[]{right, costs[right--]});
            }
        }

        return cost;
    }
}