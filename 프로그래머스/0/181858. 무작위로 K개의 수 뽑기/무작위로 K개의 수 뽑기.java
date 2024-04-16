import java.util.*;

class Solution {
    public int[] solution(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int idx = 0;
        for(int a : arr) {
            if(map.get(a)==null) map.put(a, idx++);
        } // Map<숫자, 배열인덱스>
        
        int[] answer = new int[k];
        for(int i=0; i<k; i++) answer[i] = -1;
        
        for(int num:map.keySet()) {
            if(map.get(num) < k) answer[map.get(num)] = num;
        }
        
        return answer;
    }
}