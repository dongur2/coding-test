/*
    cost[i] = i번째 계단 비용
    한 번에 1개/2개 오를 수 있음
    꼭대기에 도착하는 데 필요한 최소 비용(배열 밖)
 */
class Solution {
    int[] result;
    int answer = Integer.MAX_VALUE;

    public int minCostClimbingStairs(int[] cost) {
        result = new int[cost.length];
        Arrays.fill(result, Integer.MAX_VALUE);

        //0번에서 출발하는 경우와 1번에서 출발하는 경우 모두 계산
        dp(cost, 0, 0);
        dp(cost, 1, 0);

        return answer;
    }

    void dp(int[] cost, int curr, int c) {
        if(curr >= cost.length) {
            answer = Math.min(answer, c); return;
        }

        c += cost[curr];

        //이미 기존 값보다 비용이 같거나 초과되면... 탐색 중지 
        if(result[curr] < c || result[curr] == c) return;
        
        result[curr] = c;
        dp(cost, curr+1, c);
        dp(cost, curr+2, c);
    }
}