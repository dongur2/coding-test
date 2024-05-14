import java.util.*;

class Solution {
    boolean solution(String s) {
        if(s.length() % 2 > 0) return false;
        
        Stack<Character> st = new Stack<>();
        
        for(Character c:s.toCharArray()) {
            char before = (!st.isEmpty())? st.peek():' ';
            
            if (before == '(' && c == ')') st.pop();
            else st.push(c); 
        }
        
        return st.isEmpty()? true:false;
    }
}