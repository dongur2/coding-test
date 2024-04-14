import java.util.*;

class Solution {
    public int[] solution(String[] intStrs, int k, int s, int l) {
        List<Integer> list = new ArrayList<>();
        
        for(String str : intStrs) {
            int sliced = Integer.parseInt(str.substring(s, s+l));
            if(sliced > k) list.add(sliced);
        }
        
        return list.stream().mapToInt(n->n).toArray();
    }
}