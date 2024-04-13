import java.util.*;

class Solution {
    public int[] solution(int start, int end_num) {
        List<Integer> arr = new ArrayList<>();
        for(int i=start; i>=end_num; i--) {
            arr.add(i);
        }
        return arr.stream().mapToInt(m -> m).toArray();
    }
}