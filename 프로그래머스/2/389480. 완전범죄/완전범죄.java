class Solution {
    int limitA, limitB;
    
    int answer = Integer.MAX_VALUE; //A의 최소 누적 흔적합
    int[][][] dp;
    
    public int solution(int[][] info, int n, int m) {
        limitA = n; limitB = m;
        
        dp = new int[info.length][1001][1001]; //0:미방문, 1:가능, 2:불가능
        
        //각 물건을 a와 b가 훔쳤을 때의 경우의 수를 모두 진행 
        dfs(info, 0, info[0][0], 0);
        dfs(info, 0, 0, info[0][1]);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    boolean dfs(int[][] info, int idx, int evidA, int evidB) {
        if(dp[idx][evidA][evidB] == 2) return false;
        if(evidA >= limitA || evidB >= limitB) return false;
        
        //모든 물건을 훔쳤으면 둘 모두 잡히지 않는지 확인
        if(idx == info.length-1) {
            if(evidA < limitA && evidB < limitB) answer = Math.min(answer, evidA);
            return true;
        }
        
        boolean isValid = false;
        
        dp[idx][evidA][evidB] = dfs(info, idx+1, evidA+info[idx+1][0], evidB) ? 1 : 2;
        dp[idx][evidA][evidB] = dfs(info, idx+1, evidA, evidB+info[idx+1][1]) ? 1 : 2;
        
        return isValid;
    }
}