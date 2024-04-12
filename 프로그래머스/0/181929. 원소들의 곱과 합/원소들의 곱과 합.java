import java.util.*;

class Solution {
    public int solution(int[] num_list) {
        int sumPow = (int)Math.pow(Arrays.stream(num_list).sum(), 2);

        int mult = 1;
        for(int num:num_list) {
            mult *= num;
            if(mult > sumPow) return 0;
        }

        return 1;
    }
}