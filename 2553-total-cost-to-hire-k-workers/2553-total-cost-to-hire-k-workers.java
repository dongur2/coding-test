/*
    costs[i] = i번째 직원의 급여
    *고용 규칙:
    - k개의 세션 당 1명씩 고용
    - 긱 세션마다 직원 목록 앞, 뒤로 candidates명의 후보 중에 가장 낮은 급여인 직원을 고용
    - 급여가 같으면 -> 앞 인덱스 선택
    - candidates보다 남은 직원이 적으면 그 중에 낮은 급여
    - 각 직원은 한 번만 고용될 수 있음

    k명 직원을 고용하는 데 필요한 총비용
 */
 import java.util.Queue; import java.util.PriorityQueue;
class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        long cost = 0;

        //급여 낮은 순서대로 큐 
        //앞 후보군
        Queue<Integer> leftQ = new PriorityQueue<>((a,b) -> a-b);
        //뒤 후보군
        Queue<Integer> rightQ = new PriorityQueue<>((a,b) -> a-b);

        //앞뒤로 후보군 등록
        int left = 0, right = costs.length-1;
        for(int i=0; i<candidates && left <= right; i++) {
            leftQ.offer(costs[left++]);
        }
        for(int i=0; i<candidates && left <= right; i++) {
            rightQ.offer(costs[right--]);
        }

        //k 세션 - 한명씩 고용
        for(int i=0; i<k; i++) {
            //left가 right보다 작으면 left, 둘이 같으면 left
            if(rightQ.isEmpty() || (!leftQ.isEmpty() && leftQ.peek() <= rightQ.peek()))  {
                cost += leftQ.poll();
                if(left <= right) leftQ.offer(costs[left++]);
            }
            //right가 left보다 작으면 right
            else {
                cost += rightQ.poll();
                if(left <= right) rightQ.offer(costs[right--]);
            }

            System.out.println(i+", "+cost);
        }

        return cost;
    }
}