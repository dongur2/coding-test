import java.util.*;

class Solution {
    // 순서대로 1장씩 사용
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        Stack<String> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        
        setStack(stack1, cards1);
        setStack(stack2, cards2);
        
        for(String g:goal) {
            if(!stack1.isEmpty() && g.equals(stack1.peek())) stack1.pop();
            else if(!stack2.isEmpty() && g.equals(stack2.peek())) stack2.pop();
            else return "No";
        }
        return "Yes";
    }
    
    private void setStack(Stack<String> stack, String[] cards) {
        for(int i=cards.length-1; i>=0; i--) {
            stack.push(cards[i]);
        }
    }
}