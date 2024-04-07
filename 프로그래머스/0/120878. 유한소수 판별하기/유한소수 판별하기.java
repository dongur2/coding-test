import java.util.*;

class Solution {
    public int solution(int a, int b) {
        int deno = b / GCD(a,b);
            
        while (deno != 1) {
            if(deno % 2 == 0) {
                deno /= 2;
            } else if (deno % 5 == 0) {
                deno /= 5;
            } else {
                return 2;
            }
        }
        
        return 1;
    }
    
    /*
    * 유클리드 호제법: 두 수의 최대공약수 
    */
    private int GCD(int a, int b) {
        if(b == 0) {
            return a;
        } else {
            return GCD(b, a % b);
        }
    }
    
}