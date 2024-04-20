import java.util.stream.*;

class Solution {
    public long[] solution(int x, int n) {
        // return LongStream.rangeClosed(0, n-1).map(l -> l = x + (x*l)).toArray();
        
        long[] arr = new long[n];
        for(int i=0; i<n; i++) {
            arr[i] = x + ((long)x * i);
        }
        return arr;
    }
}