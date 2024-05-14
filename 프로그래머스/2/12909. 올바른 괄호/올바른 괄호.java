import java.util.*;

class Solution {
    private boolean useStack(String s) {
        if(s.length() % 2 > 0) return false;
        
        Stack<Character> st = new Stack<>();
        
        for(Character c:s.toCharArray()) {
            char before = (!st.isEmpty())? st.peek():' ';
            
            if (before == '(' && c == ')') st.pop();
            else st.push(c); 
        }
        
        return st.isEmpty()? true:false;
    }
    
    private boolean useCnt(String s) {
        int cnt = 0;
        
        for(Character c:s.toCharArray()) {
            if(c == '(') cnt++;
            else if(c == ')') cnt--;
            
            if(cnt < 0) return false;
        }
        return (cnt == 0)? true:false;
    }
    
    boolean solution(String s) {
        return useCnt(s);
    }
}