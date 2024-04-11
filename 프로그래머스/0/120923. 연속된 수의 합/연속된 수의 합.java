import java.util.*;

class Solution {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
        
        for(int i=(total/num) + (num/2)+1; ; i--) {
            int sum = 0;
            for(int j=0; j<num; j++) {
                sum += i-j;
                answer[j] = i-j;
            }
            
            if(sum==total){
                Arrays.sort(answer);
                return answer;
            }
        }
        
    }
}