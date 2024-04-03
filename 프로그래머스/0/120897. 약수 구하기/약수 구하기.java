import java.util.stream.*;
import java.util.*;

class Solution {
    private int[] useStream(int n) {
        return IntStream.rangeClosed(1, n).filter(num -> n % num == 0).toArray();
    }
    
    private int[] useFor(int n) {
        List<Integer> num = new ArrayList<>();
        
        for(int i=1; i<=n; i++) {
            if(n % i == 0) num.add(i);
        }
        
        int[] answer = new int[num.size()];
        for(int i=0; i<num.size(); i++) {
            answer[i] = num.get(i);
        }
        
        return answer;
    }
    
    public int[] solution(int n) {
        return useFor(n);
    }
}