import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        if(arr.length == 1) return new int[] {-1};
        
        int min = Arrays.stream(arr).min().getAsInt();
        // int min = Integer.MAX_VALUE;
        // for(int i=0; i<arr.length; i++) {
        //     min = (arr[i] < min)? arr[i] : min;
        // }
        // final int rmv = min;
        
        return Arrays.stream(arr).filter(a -> a != min).toArray();
    }
}