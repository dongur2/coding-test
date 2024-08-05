import java.util.*;

class Solution {
    /*
    ** 처음부터 끝까지 회전시켰을 때, 올바른 괄호 문자열이 되는 횟수를 반환
    */
    public int solution(String s) {
        int answer = 0;
        String temp = s + s;
        
        //1.한칸씩 뒤로 윈도우 이동
        for(int i=0; i<s.length(); i++) {
            String t = temp.substring(i, i+s.length());
            
            //2.올바른 괄호로만 이루어져있는지 검사
            if(isValid(t)) answer++;
        }
        return answer;
    }
    
    boolean isValid(String word) {
        Stack<Character> stack = new Stack<>();
            for(char c : word.toCharArray()) {
                if(c == '[' || c == '{' || c == '(') stack.push(c);
                else {
                    if(stack.isEmpty()) return false;
                    else if(c == ']' && stack.peek() == '[') stack.pop();
                    else if(c == '}' && stack.peek() == '{') stack.pop();
                    else if(c == ')' && stack.peek() == '(') stack.pop();
                }
            }
        return stack.isEmpty();
    }
}

