import java.util.*;

class Solution {
    public int solution(int[] nums) {
        List<Integer> sum = new ArrayList<>();
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                for(int l=j+1; l<nums.length; l++) {
                    sum.add(nums[i] + nums[j] + nums[l]);
                }
            }
        }
        
        int cnt = 0;
        for(int s:sum) {
            int d = 0;
            for(int i=1; i<=s; i++) {
                if(s % i == 0) d++;
                if(d > 2) break;
            }
            if(d==2) cnt++;
        }
        
        return cnt;
    }
}