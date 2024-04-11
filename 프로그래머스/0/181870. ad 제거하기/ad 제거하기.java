import java.util.*;

class Solution {
    private String[] useStream(String[] strArr) {
        return Arrays.stream(strArr).filter(s -> !s.contains("ad")).toArray(String[]::new);
    }
    
    private String[] useFor(String[] strArr) {
        List<String> list = new ArrayList<>();
        
        for(String s:strArr) {
            if(!s.contains("ad")) list.add(s);
        }
        
        String[] answer = new String[list.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    public String[] solution(String[] strArr) {
        return useFor(strArr);
    }
}