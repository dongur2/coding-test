import java.util.*;

class Solution {
    public int[] solution(int[] num_list) {
        Arrays.sort(num_list);
        
        int[] answer = new int[num_list.length-5];
        for(int i=0; i<answer.length; i++) {
            answer[i] = num_list[num_list.length-1-i];
        }
        
        Arrays.sort(answer);
        return answer;
    }
}