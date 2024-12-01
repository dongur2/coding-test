class Solution {
    /*
    Q. 주어진 가격을 만들기 위해 필요한 '동전의 최소 개수'를 리턴 (만들 수 없으면 '-1')
    - 각 동전의 개수는 무한
    */

    Map<Integer, Integer> coinsMap;
    
    public int coinChange(int[] coins, int amount) {
        coinsMap = new HashMap<>();
        return bfs(coins, amount);
    }
    
    int bfs(int[] coins, int amount) {
        Queue<Integer> q = new ArrayDeque<>();
        
        //start
        q.offer(amount);
        coinsMap.put(amount, 0);
        
        //q
        while(!q.isEmpty()) {
            int remainAmt = q.poll();
            
            for(int coin : coins) {
                int nxtAmt = remainAmt - coin;
                
                if(nxtAmt >= 0 && !coinsMap.containsKey(nxtAmt)) {
                    q.offer(nxtAmt);
                    coinsMap.put(nxtAmt, coinsMap.get(remainAmt)+1);
                    if(nxtAmt == 0) return coinsMap.get(remainAmt)+1;
                } 
                
                if(remainAmt == 0) return coinsMap.get(remainAmt);
            }
        }
        
        return -1;
    }
}