import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int[] pre = Arrays.copyOfRange(arr, 0, arr.length);
        
        int x=0;
        while(true) {
            for(int i=0; i<arr.length; i++) {
                if(arr[i] >= 50 && arr[i] % 2 == 0) arr[i] /= 2;
                else if (arr[i] < 50 && arr[i] % 2 > 0) arr[i] = arr[i]*2+1;
            }
            
            if (Arrays.compare(pre, arr) == 0) return x;
            else {
                pre = Arrays.copyOfRange(arr, 0, arr.length);
                x++;
            }
        }
        
    }
}