import java.util.*;

class Solution {
    private int[] useStream(int[] arr, int divisor) {
        int[] answer = Arrays.stream(arr).filter(a -> a % divisor == 0).toArray();
        
        if (answer.length > 0) Arrays.sort(answer);
        else answer = new int[] {-1};
        
        return answer; 
    }
    
    private int[] useList(int[] arr, int divisor) {
        List<Integer> list = new ArrayList<>();
        
        for(int a : arr) {
            if(a % divisor == 0) list.add(a);
        }
        
        if(list.size() > 0) {
            list.sort(Comparator.naturalOrder());
            return list.stream().mapToInt(l->l).toArray();
        }
        else return new int[] {-1};
        
    }
    
    public int[] solution(int[] arr, int divisor) {
        return useList(arr, divisor);
    }
}