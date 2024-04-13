import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack();
        
        int i = 0; 
        while (i < arr.length) {
            if(stack.empty() || stack.peek() < arr[i]) stack.push(arr[i++]);
            else stack.pop(); 
            
        }
        
        int[] stk = new int[stack.size()];
        for(int j=stack.size()-1; j>=0; j--) {
            stk[j] = stack.pop();
        }
        
        return stk;
    }
}