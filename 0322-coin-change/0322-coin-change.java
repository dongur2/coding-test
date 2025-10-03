class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);

        dp[0] = 0;

        for(int price=1; price<=amount; price++) {
            for(int coin:coins) {
                if(price >= coin) dp[price] = Math.min(dp[price], dp[price-coin]+1);
            }
        }

        return dp[amount] == amount+1 ? -1 : dp[amount];
    }
}