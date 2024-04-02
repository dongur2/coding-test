import java.util.stream.*;

class Solution {
    private int myCode(int n) {
        int answer = 0;
        for(int i=0; i<=n; i+=2) answer+=i;
        
        return answer; 
    }
    
    private int useStream(int n) {
        return IntStream.rangeClosed(0, n).filter(num -> num % 2 == 0).sum();
    }
    
    public int solution(int n) {
        return useStream(n);
    }
}