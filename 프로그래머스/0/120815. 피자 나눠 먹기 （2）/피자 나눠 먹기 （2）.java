import java.util.*;

class Solution {
    private int myCode(int n) {
        // 6조각과 사람수의 최소공배수 / 6 = 피자개수
        // 6조각과 사람수의 최대공약수의 배수 중 최소공배수 존재
        int[] factorsOfSix = {1, 2, 3, 6};
        
        int maxFactor = 1;
        for(int factor : factorsOfSix) {
            if(n % factor == 0) {
                maxFactor = factor;
            }
        }
        
        int i = 1;
        while(true) {
            int minMultiple = maxFactor * i;
            if(minMultiple % 6 == 0 && minMultiple % n == 0) {
                return minMultiple / 6;
            } else {
                i++;
            }
        }
    }
    
    private int reference(int n) {
        int answer = 1;
        
        while(true){
            if(6 * answer % n == 0) break;
            answer++;
        }
        
        return answer;
    }
    
    public int solution(int n) {
        return reference(n);
    }
}