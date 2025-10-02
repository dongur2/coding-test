import java.util.Map; import java.util.HashMap;

class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0; 

        Map<Integer, Integer> map = new HashMap<>(); //가격:필요동전개수 - 최소값으로 업데이트 
        //0원은 0개
        map.put(0, 0);

        for(int coin : coins) {
            for(int price=coin; price<=amount; price++) {
                //현재 동전을 포함해서 가격을 만들기 위해 (가격-동전)원을 만드는 최소 동전 개수를 가져옴
                int prev = map.getOrDefault(price-coin, -1);

                //(가격-동전)원을 만드는 동전 개수값이 없으면 현재 동전을 포함해서 지금 가격 만들기 불가능
                if(prev == -1) continue;

                //(가격-동전)원을 만드는 동전 개수값은 있는데 현재 가격에 대한 저장된 값이 없으면 - 아직 한 번도 만든 적 없으므로 바로 개수를 최소로 저장
                if(!map.containsKey(price)) map.put(price, prev+1);
                //저장된 값이 있으면 최소인지 비교해서 업데이트
                else map.put(price, Math.min(map.get(price), prev+1));
            }            
        }

        return map.containsKey(amount) ? map.get(amount) : -1;
    }
}