import java.util.*;

class Solution {
    Map<Integer, Integer> coinsMap;

    public int coinChange(int[] coins, int amount) {
        coinsMap = new HashMap<>();
        return countCoin(coins, amount);
    }

    private int countCoin(int[] coins, int amount) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(amount); coinsMap.put(amount, 0); //만들어야하는 총 금액을 루트 노드로 시작

        while(!q.isEmpty()) {
            int cur = q.poll(); //현재 노드(남은 금액)

            for(int coin:coins) {
                int nxt = cur - coin; //현재 금액에서 각 동전을 거슬러 주면 남는 금액
                if(!coinsMap.containsKey(nxt) && nxt >= 0) { //남은 금액을 방문한 적 없고 남은 금액이 0이상일 때 대기열 추가 & 동전 카운트(이전 금액 동전+1)
                    q.offer(nxt); coinsMap.put(nxt, coinsMap.get(cur)+1); 
                    if(nxt == 0) return coinsMap.get(nxt); // 남은 금액이 0이 될 경우 즉시 사용한 동전 개수 리턴
                } else if(cur == 0) return coinsMap.get(cur); // 현재 노드(남은 금액)가 0이면 사용한 동전 개수 리턴
            }
        }

        //남은 금액이 0인 경우가 없이 모든 노드 탐색을 끝난 경우: 금액을 만들 수 없으므로 -1 리턴
        return -1;
    }
}