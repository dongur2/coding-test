//amount원을 만드는 데 필요한 최소 동전 개수 (불가능하면 -1)
class Solution {
    public int coinChange(int[] coins, int amount) {
        final int INF = Integer.MAX_VALUE;

        /*
            인덱스 == 가격
            counts[i] = i원을 만들기 위해 필요한 동전 '최소' 개수 
         */
        int[] counts = new int[amount+1];
        Arrays.fill(counts, INF); //기본값 INF 설정 (주어진 동전으로 i원을 만들 수 없으면 INF 유지)
        counts[0] = 0; //0원은 0

        //1원부터 amount원까지 1원씩 올려가면서 주어진 동전으로 만들 수 있는지 확인
        for(int price=1; price<=amount; price++) {
            //각각 동전마다 시도
            for(int coin:coins) {
                //동전을 추가했을 때, 만들어야하는 남은 금액이 음수가 아닐 때 (음수면 이미 동전 금액이 초과) 
                //+ 남은 금액을 주어진 동전으로 만들 수 있을 때 (counts[남은 금액]에 저장된 값은: 해당 금액을 만드는 데 필요한 최소 동전 개수이므로 +1만 하면 됨)
                int remains = price - coin;
                if(remains >= 0 && counts[remains] != INF) counts[price] = Math.min(counts[price], counts[remains]+1); //이미 price를 만들었던 적이 있을 수도 있으니까 최소값으로 바인딩
            }
        }

        return counts[amount] == INF ? -1 : counts[amount];
    }
}