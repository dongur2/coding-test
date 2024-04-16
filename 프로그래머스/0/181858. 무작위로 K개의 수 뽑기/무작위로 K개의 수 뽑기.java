import java.util.*;

class Solution {
    private int[] myWithMap(int[] arr, int k) {
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
    
    private int[] useArrayList(int[] arr, int k) {
        List<Integer> list = new ArrayList<>();
        
        for(int num : arr) {
            if(!list.contains(num)) list.add(num);
            continue;
        }
        
        if(list.size() == k) return list.stream().mapToInt(Integer::intValue).toArray();
        else if(list.size() > k) {
            return Arrays.copyOfRange(list.stream().mapToInt(Integer::intValue).toArray(), 0, k);
        } else {
            int[] answer = new int[k];
            for(int i=0; i<k; i++) {
                if(i<list.size()) answer[i] = list.get(i);
                else answer[i] = -1;
            }
            return answer;
        }
        
    }
    
    public int[] solution(int[] arr, int k) {
        return useArrayList(arr, k);
    }
}