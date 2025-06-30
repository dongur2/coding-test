import java.util.Set;
import java.util.HashSet;

//최소 2번 중복해서 포함되는 숫자의 존재 여부 확인
class Solution {
    public boolean containsDuplicate(int[] nums) { //10^5
        Set<Integer> set = new HashSet<>();

        //O(N)
        for(int n:nums) {
            if(set.contains(n)) return true; //O(1)
            set.add(n);
        }

        return false;
    }
}