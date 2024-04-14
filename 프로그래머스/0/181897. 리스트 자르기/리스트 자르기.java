import java.util.*;

class Solution {
    private int[] my(int n, int[] slicer, int[] num_list) {
        int a = slicer[0], b = slicer[1], c = slicer[2];
        
        int[] answer = {};
        
        switch(n) {
            case 1: answer = Arrays.copyOfRange(num_list, 0, b+1); break;
            case 2: answer = Arrays.copyOfRange(num_list, a, num_list.length); break;
            case 3: answer = Arrays.copyOfRange(num_list, a, b+1); break;
            case 4: {
                List<Integer> list = new ArrayList<>();
                for(int i = a; i < b+1; i+=c) {
                    list.add(num_list[i]);
                }
                
                answer = list.stream().mapToInt(l->l).toArray(); break;
            }
        }
        
        return answer;
    }
    
    private int[] refer(int n, int[] slicer, int[] num_list) {
        int start = (n == 1)? 0 : slicer[0];
        int end = (n == 2)? num_list.length - 1 : slicer[1];
        int step = (n == 4)? slicer[2] : 1;
        
        int j=0;
        int[] answer = new int[(end - start + step) / step];
        for(int i=start; i<=end; i+=step) {
            answer[j++] = num_list[i];
        }
        return answer;
    }
    
    public int[] solution(int n, int[] slicer, int[] num_list) {
        return refer(n, slicer, num_list);
    }
}