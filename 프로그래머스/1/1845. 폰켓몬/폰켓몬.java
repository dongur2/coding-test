import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int pick = nums.length/2;
  
        Set<Integer> set = new HashSet<>();
        for(int n:nums) {
            set.add(n);
        } 
                
        return (set.size() > pick)? pick:set.size();
    }
}