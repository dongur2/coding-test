import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        Stack<Integer> stk = new Stack<>();
        
        int i = 0;
        while(i < arr.length) {
            if(stk.isEmpty() || stk.peek() != arr[i]) stk.push(arr[i++]);
            else {
                stk.pop();
                i++;
            }
        }

        return stk.isEmpty()? 
            new int[] {-1} : stk.stream().mapToInt(Integer::intValue).toArray();
    }
}