import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        int[] answer = Arrays.stream(arr).filter(a -> a % divisor == 0).toArray();
        
        if (answer.length > 0) Arrays.sort(answer);
        else answer = new int[] {-1};
        
        return answer;
    }
}