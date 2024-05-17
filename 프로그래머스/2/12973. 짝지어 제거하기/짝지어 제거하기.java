import java.util.*;

class Solution{
    public int solution(String s) {
        Stack<Character> st = new Stack<>();
        for(char c:s.toCharArray()) {
            if(!st.isEmpty() && c==st.peek()) st.pop();
            else st.push(c);
        }
        
        return (st.isEmpty())? 1:0;
    }
}