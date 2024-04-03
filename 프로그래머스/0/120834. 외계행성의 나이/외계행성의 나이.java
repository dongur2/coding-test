import java.util.*;

class Solution {
    public String solution(int age) {
        String[] table = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        // 48, 49, 50 ,51 ...
        
        StringBuilder answer = new StringBuilder();
        
        String stringAge = age+"";
        for(int i=0; i<stringAge.length(); i++) {
            String ch = table[(int)stringAge.charAt(i) - 48];
            answer.append(ch);
        }
        
        return answer.toString();
    }
}