import java.util.*;

class Solution {
    private int[] my(long n) {
        String[] num = String.valueOf(n).split("");
        
        int[] answer = new int[num.length];
        for(int i=0, j=num.length-1; i<num.length; i++, j--) {
            answer[i] = Integer.parseInt(num[j]);
        }
        return answer;  
    }
    
    private int[] useStream(long n) {
        return new StringBuilder().append(n).reverse().chars().map(c -> Character.getNumericValue(c)).toArray();
    }
    
    public int[] solution(long n) {
        return useStream(n);
    }
}