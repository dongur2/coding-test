//주어진 가격을 만드는 데 필요한 동전 개수 (불가능하면 -1)
class Solution {
    public int coinChange(int[] coins, int amount) {
        //table[i] = 금액 i를 만드는 데 필요한 최소 동전 개수
        int[] table = new int[amount + 1]; 
        Arrays.fill(table, Integer.MAX_VALUE); //기본값 INF
        table[0] = 0; //출발 

        //1원부터 amount까지 계산 
        for(int i = 1; i <= amount; i++) {
            //각 동전 시도 
            for(int coin:coins) {
                //i-coin이 음수가 아니고, 그 금액을 만들 수 있으면 시도('동전을 더하기 전 금액'을 만들 수 있었는지 확인)
                if(i - coin >= 0 && table[i - coin] != Integer.MAX_VALUE) {
                    //'동전을 더하기 전 금액을 만든 방법 개수 + 1'이 총 방법 개수: 기존 값보다 작을 경우에만 업데이트
                    table[i] = Math.min(table[i], 1 + table[i - coin]);
                }
            }
        }
        return (table[amount] == Integer.MAX_VALUE) ?  -1 : table[amount];
    }
}