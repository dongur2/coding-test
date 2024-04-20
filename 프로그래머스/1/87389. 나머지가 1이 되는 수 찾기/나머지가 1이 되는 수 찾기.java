import java.util.stream.*;

class Solution {
    public int solution(int n) {
        // return IntStream.rangeClosed(1, n).filter(l -> n % l == 1).findFirst().orElse(n);
        
        int x = 0;
        for(int i=1; i<=n; i++) {
            if(n % i == 1) {
                x = i; 
                break;
            }
        }
        return x;
    }
}