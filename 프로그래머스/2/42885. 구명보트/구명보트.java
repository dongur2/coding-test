import java.util.*;

class Solution {
    /*
        필요한 구명보트의 최솟값 리턴
        - 최대 2명
        - 무게 제한 limit
        
        50 50 70 80 -- 80, 70, 50-50
        무거운 사람부터 가장 가벼운 사람이랑 합해서 제한을 넘지 않는지 계산
    */
    
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int boat = 0;
        int idx = 0; // 가벼운 사람
        for(int i=people.length-1; i>=idx; i--) {
            if(people[i] + people[idx] <= limit) { // 제한 넘지 않으면 2명
                boat++;
                idx++;
            } else boat++; // 제한 넘을 경우 무거운 사람만 탑승 (idx유지)
        }
        return boat;
    }
}