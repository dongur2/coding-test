import java.util.*;

class Solution {
    public int[] solution(int l, int r) {
        List<Integer> arr = new ArrayList<>();
        
        for(int i=l; i<=r; i++) {
            String converted = (i+"").replaceAll("[05]","");
            if(converted.length() == 0) arr.add(i);
        }
        
        return (arr.size() > 0)? arr.stream().mapToInt(n -> n).toArray() : new int[] {-1};
    }
}