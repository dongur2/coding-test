import java.util.stream.*;

class Solution {
    private int[] myCode(int n) {
        int size = (n % 2 == 0)? n/2 : n/2 + 1;
        int[] answer = new int[size];
        
        for(int i=0; i<answer.length; i++) {
            answer[i] = 1 + 2*i;
        }
        
        return answer;
    }
    
    private int[] useStream(int n) {
        return IntStream.rangeClosed(1, n).filter(number -> number % 2 == 1).toArray();
    }
    
    
    public int[] solution(int n) {
        return useStream(n);
    }
}