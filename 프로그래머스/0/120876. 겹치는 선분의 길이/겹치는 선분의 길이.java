import java.util.*;

class Solution {
    private int useArray(int[][] lines) {
        int answer = 0;
        
        int[] line = new int[201];
        
        for(int[] i : lines) {
            int start = i[0] + 100;
            int end = i[1] + 100;
            
            for(int j=start; j<end; j++) {
                line[j]++;
            }
        }
        
        for(int i : line) {
            if(i>1) answer++;
        }
        
        return answer;  
    }
    
    private int useMap(int[][] lines) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i<3; i++) {
            int min = Math.min(lines[i][0], lines[i][1]);
            int max = Math.max(lines[i][0], lines[i][1]);
            
            for(int j=min; j<max; j++) {
                map.put(j, map.getOrDefault(j, 0) + 1);
            }
        }
        
        // for(int key : map.keySet()) {
        //     if(map.get(key) > 1) count++;
        // }
        return (int)map.values().stream().filter(v -> v > 1).count();
    }
    
    public int solution(int[][] lines) {
        return useMap(lines);
    }
}