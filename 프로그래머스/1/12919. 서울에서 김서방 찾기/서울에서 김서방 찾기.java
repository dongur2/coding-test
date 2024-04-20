import java.util.*;

class Solution {
    private int useFor(String[] seoul) {
        int idx = 0;
        for(int i=0; i<seoul.length; i++) {
            if(seoul[i].equals("Kim")) {
                idx = i;
                break;
            }
        }
        return idx;
    }
    
    private int useArray(String[] seoul) {
        return Arrays.asList(seoul).indexOf("Kim");
    }
    
    public String solution(String[] seoul) {
        int answer = useArray(seoul);
        
        return "김서방은 " + answer +"에 있다";
    }
}