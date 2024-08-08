import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        String t = s + s;
            
        for(int i=0; i<s.length(); i++) {
            if(isValid(t.substring(i, i+s.length()))) answer++;
        }
        
        return answer;
    }
    
    boolean isValid(String word) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char w:word.toCharArray()) {
            if(w == '(' || w == '{' || w == '[') stack.push(w);
            else {
                if(stack.isEmpty()) return false;
                else if(stack.peek() == '(' && w == ')' 
                        || stack.peek() == '{' && w == '}' 
                        || stack.peek() == '[' && w == ']') stack.pop();
                else return false;
            }
        }
        return stack.isEmpty();
    }
}