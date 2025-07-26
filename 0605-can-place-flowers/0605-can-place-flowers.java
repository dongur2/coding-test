/*
    화단에 꽃을 인접하게 심을 수 없음
    0: 비었음, 1: 꽃 
    새로운 꽃 n송이를 화단에 인접하지 않게 모두 심을 수 있으면 true 리턴
*/
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int f = 0;

        for(int i=0; i<flowerbed.length; i++) {
            if(f >= n) break;

            //비었으면 주변을 확인하고 심기 
            if(flowerbed[i] == 0) {
                if(i > 0) {
                    if(flowerbed[i-1] != 0) continue;
                }

                if(i < flowerbed.length-1 ) {
                    if(flowerbed[i+1] != 0) continue;
                }

                flowerbed[i] = 1;
                f++;
            }
        }

        return f == n;
    }
}