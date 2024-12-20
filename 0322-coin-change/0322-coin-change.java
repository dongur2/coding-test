import java.util.*;

/*
Q. 주어진 동전으로 목표 금액을 만들 수 있을 때, 필요한 동전의 "최소 개수"를 리턴
- 만들 수 없으면 -1 리턴

- 최소 개수니까 bfs 적용
*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        return bfs(coins, amount);        
    }
    
    int bfs(int[] coins, int amount) {
        //setting
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(amount, 0));
        
        Set<Integer> log = new HashSet<>();
        
        //queue
        while(!q.isEmpty()) {
            //now
            Node cur = q.poll();
            int curAmt = cur.now;
            int curCnt = cur.cnt;
            
            if(curAmt == 0) return curCnt;
            
            //next
            for(int coin:coins) {
                int nxtAmt = curAmt - coin;
                
                if(nxtAmt >= 0 && !log.contains(nxtAmt)) {
                    if(nxtAmt == 0) return curCnt+1;
                    
                    q.offer(new Node(nxtAmt, curCnt+1));
                    log.add(nxtAmt);   
                }
            }
        }
        return -1;
    }
    
    class Node {
        int now;
        int cnt;
        
        public Node(int now, int cnt) {
            this.now = now;
            this.cnt = cnt;
        }
    }
}