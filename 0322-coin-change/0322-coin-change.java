import java.util.*;

class Solution {
    /*
    ** 주어진 동전 종류로 주어진 금액을 만들 수 있는 최소 동전 개수를 반환
    - 만들 수 없으면 -1을 반환
    - 각 동전마다 사용할 수 있는 횟수는 제한 없음
    
    최소 동전 개수를 구하므로 BFS로 접근
    각 동전을 하나씩 뺐을 때 남는 금액이 노드: 다음 연결 노드가 0이 될 때 카운트를 반환
    */
    
    Map<Integer, Integer> map; // {남은 금액:필요 동전 개수}
    
    public int coinChange(int[] coins, int amount) {
        map = new HashMap<>();
        
        return bfs(coins, amount);
    }
    
    int bfs(int[] coins, int amount) {
        //시작점
        if(amount == 0) return 0;
        
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(amount); 
        map.put(amount, 0);
        
        //대기열에서 방문
        while(!q.isEmpty()) {
            int curAmount = q.poll();
            int curCoinCnt = map.get(curAmount);
            
            //인접 노드 탐색 후 대기열 추가
            for(int i=0; i<coins.length; i++) {
                int nextAmount = curAmount - coins[i];
                if(nextAmount == 0) return curCoinCnt + 1; //다음 노드가 0이면 즉시 종료
                
                if(map.get(nextAmount) != null || nextAmount < 0) continue;
                
                q.offer(nextAmount); 
                map.put(nextAmount, curCoinCnt + 1); 
            }
        }
        return -1;
    }
}