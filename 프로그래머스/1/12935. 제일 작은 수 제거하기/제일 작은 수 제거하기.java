import java.util.*;

class Solution {
    public int[] useStream(int[] arr) {
        if(arr.length == 1) return new int[] {-1};
        int min = Arrays.stream(arr).min().getAsInt();
        return Arrays.stream(arr).filter(a -> a != min).toArray();
    }
    
    public int[] solution(int[] arr) {
        if(arr.length == 1) return new int[] {-1};
        
        int min = Integer.MAX_VALUE;
        for(int i=0; i<arr.length; i++) {
            min = (arr[i] < min)? arr[i] : min;
        }
        
        int j = 0;
        int[] answer = new int[arr.length - 1];
        for(int i=0; i<arr.length; i++) {
            if(arr[i] != min) answer[j++] = arr[i];
        }
        
        return answer;
    }
}