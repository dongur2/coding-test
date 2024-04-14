import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[][] intervals) {
        int cnt = (intervals[0][1] - intervals[0][0] + 1) + (intervals[1][1] - intervals[1][0] + 1);
        int[] answer = new int[cnt];
        
        for(int i=0, j=0; i<2; i++) {
            for(int num : Arrays.copyOfRange(arr, intervals[i][0], intervals[i][1]+1)) {
                answer[j++] = num;
            }    
        }
                
        return answer;
    }
}