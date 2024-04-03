import java.util.*;
import java.util.stream.*;

class Solution {
    private int[] useFor(int n, int[] numlist) {
        List<Integer> multiples = new ArrayList<>();
        for(int num : numlist) {
            if(num % n == 0) multiples.add(num);
        }
    
        int[] answer = new int[multiples.size()];
        for(int i=0; i<multiples.size(); i++) {
            answer[i] = multiples.get(i);
        }
        
        return answer;
    }
    
    private int[] forStream(int n, int[] numlist) {
        return Arrays.stream(numlist).filter(num -> num % n == 0).toArray();
    }
    
    public int[] solution(int n, int[] numlist) {
        return useFor(n, numlist);
    }
}