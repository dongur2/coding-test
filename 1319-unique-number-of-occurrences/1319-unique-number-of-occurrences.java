import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
/*
    숫자 배열 arr이 주어졌을 때, 배열의 '각 숫자의 개수'가 서로 유일한지 리턴 
 */
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int a:arr) {
            map.put(a, map.getOrDefault(a, 0)+1);
        }

        Set<Integer> val = new HashSet<>(map.values());
        return val.size() == map.size();
    }
}