import java.util.*;

class Solution {
    public int solution(int[] array, int n) {
        Arrays.sort(array);
        
        int answer = array[0];
        for(int num : array) {
            if(Math.abs(Math.abs(answer) - n) > Math.abs(Math.abs(num) - n)) {
                answer = num;
            }
        }
        
        return answer;
    }
}