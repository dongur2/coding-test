import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[] delete_list) {
        // arr에서 delete_list원소 모두 삭제
        for(int i=0; i<arr.length; i++) {
            for(int d:delete_list) {
                if(arr[i] == d) arr[i] = 0;
            }
        }
        
        return Arrays.stream(arr).filter(n -> n > 0).toArray();
    }
}