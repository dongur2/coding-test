import java.util.*;

class Solution {
    public int[] solution(int n) {
        List<Integer> list = new ArrayList<>();
        list.add(n);
        
        int now = n;
        while(now > 1) {
            now = (now % 2 == 0)? now/2 : 3*now+1;
            list.add(now);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}