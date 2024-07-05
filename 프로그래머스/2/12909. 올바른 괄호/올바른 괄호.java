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
            if(ch == '(') stack.push(ch); // '('일 경우 스택에 삽입
            else { // ')'일 경우 
                if(!stack.isEmpty() && stack.peek() == '(') stack.pop(); // 스택에 '('가 들어있으면 '()'짝이 되므로 삭제
                else stack.push(ch); // 스택에 '('가 없으면 ')'를 스택에 삽입
            }
        }
        
        return stack.isEmpty(); // 스택이 비었으면 괄호짝이 맞으므로 true, 안비었으면 짝이 안맞으니 false
    }
}