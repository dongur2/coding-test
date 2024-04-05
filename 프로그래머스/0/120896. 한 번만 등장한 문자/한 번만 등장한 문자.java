import java.util.*;

class Solution {
    public String solution(String s) {
        if(s.length() == 1) return s;
        
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        
        Set<Character> keys = new LinkedHashSet<>();
        for(char c : chars) {
            keys.add(c);
        }
        
        StringBuffer answer = new StringBuffer();
        for(char c : keys) {
            if(s.indexOf(c) == s.lastIndexOf(c)) answer.append(c);
        }        
        
        return answer.toString();
    }
}