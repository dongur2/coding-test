import java.util.*;

class Solution {
    //10^6
    public int solution(int n) {
        int[] arr = new int[n+1];
        Arrays.fill(arr, 1);
        
        for(int i=2; i<=(int)Math.sqrt(n); i++) {
            if(arr[i] == 1) {
                int j = 2;
                while(i*j <= n) {
                    arr[i*j] = 0;
                    j++;
                }
            }
        }
        
        return (int)Arrays.stream(arr).filter(a->a==1).count()-2; //0,1 제외
    }
}