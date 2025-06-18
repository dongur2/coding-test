//가장 윗계단으로 올라갈 수 있는 최소 비용
class Solution {
    static final int INF = Integer.MAX_VALUE;

    //cost[i]: i번째 계단의 비용
    public int minCostClimbingStairs(int[] cost) {    
        int top = cost.length;

        int[] minCosts = count(cost, top);
        
        //top층(비용 없음) 비용 계산
        minCosts[top] = Math.min(minCosts[top-2], minCosts[top-1]);
        return minCosts[top];
    }

    private static int[] count(int[] cost, int top) {
        //각 계단까지의 최소 비용 저장 
        int[] res = new int[top+1];
        Arrays.fill(res, INF);

        //시작점 비용 바인딩 (0층과 1층은 처음 시작할 수 있는 위치이므로 비용 고정)
        res[0] = cost[0];
        res[1] = cost[1];

        //[1칸 이동해서 도착한 비용]과 [2칸 이동해서 도착한 비용] 중 작은 비용에다 도착한 층의 비용을 더한 것이 최종 비용
        //최종 비용을 기존 저장된 최소 비용과 비교해서 더 작은 비용을 저장 
        for(int i=1; i<top; i++) {
            //1층은 [0층비용+1층비용]과 [1층비용] 중 최소 비교 
            if(i == 1) {
                res[1] = Math.min(res[0]+cost[1], res[1]); continue;
            }

            res[i] = Math.min(res[i], Math.min(res[i-1], res[i-2])+cost[i]);
        }

        return res;
    }
}