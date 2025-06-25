//얻을 수 있는 최대 이익 (어떤 이익도 얻을 수 없으면 0)
class Solution {
    /* 
        price[i] = i번째 날의 상품 가격
        이익 = 판매한 날의 가격 - 구매한 날의 가격 
     */
    public int maxProfit(int[] prices) {
        int buy = Integer.MAX_VALUE;
        int max = 0;

        //O(10^5)
        for(int i=0; i<prices.length; i++) {
            //이전보다 낮은 가격에 살 수 있으면 재구입 
            if(prices[i] < buy) buy = prices[i];

            //오늘 팔면 얻는 이익 
            int profit = prices[i] - buy;

            //이익이 이전에 얻었던 이익보다 크면 업데이트
            max = Math.max(max, profit);
        }

        return max;
    }
}