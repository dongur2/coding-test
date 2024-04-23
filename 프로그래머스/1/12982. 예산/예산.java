import java.util.*;

class Solution {
    public int solution(int[] d, int budget) {
        Arrays.sort(d);
        
        int cnt = 0, sum = 0;
        for(int price : d) {
            sum += price;
            if(sum <= budget) cnt++;
        }
        
        return cnt;
    }
}