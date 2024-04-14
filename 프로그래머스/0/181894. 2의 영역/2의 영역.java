import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        List<Integer> idx = new ArrayList<>();
        
        // 2 인덱스
        for(int i=0; i<arr.length; i++) {
            if(arr[i] == 2) idx.add(i);
        }
        
        if(idx.isEmpty()) return new int[] {-1};
        if(idx.size() == 1) return new int[] {arr[idx.get(0)]};
        
        int start = idx.get(0);
        int end = idx.get(idx.size() - 1);
        return Arrays.copyOfRange(arr, start, end+1);
    }
}