import java.util.*;

class Solution {
    public int solution(String[] strArr) {
        Map<Integer, Integer> map = new HashMap<>(); // 길이:개수
        
        for(String str:strArr) {
            map.put(str.length(), map.getOrDefault(str.length(), 0) + 1);
        }
        
        int max = 0;
        for(int length:map.keySet()) {
            max = map.get(length) > max? map.get(length):max;
        }
        return max;
    }
}