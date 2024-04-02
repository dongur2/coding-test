import java.util.*;

class Solution {
    private int useStream(int[] array, int height) {
        return (int)Arrays.stream(array).filter(man -> man > height).count();
    }
    
    private int useFor(int[] array, int height) {
        int answer = 0;
        for(int man : array) {
            if(man > height) answer++;
        }
        return answer;
    }
    
    public int solution(int[] array, int height) {
        return useFor(array, height);
    }
}