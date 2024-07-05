import java.util.*;

class Solution {
    boolean solution(String s) {
        /*
        1. 예시를 보면, ()() -> 어떻게 해야 올바른걸 판단? -- 생각..
        열린게 들어오면, 닫힌게 올때까지 기다림
        닫힌게 오면 해소
        2. (())() -> 연속으로 열린거.. -> 닫히는거 기다림기다림 --> 들어오면 해소
        */
        
        Stack<Character> stack = new Stack<>();
        
        for(char ch : s.toCharArray()) {
            if(ch == '(') stack.push(ch);
            else {
                if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
                else stack.push(ch);
            }
        }
        
        return stack.isEmpty();
    }
}