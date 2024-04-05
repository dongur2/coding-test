import java.util.*;

class Solution {
    private int myCode(String s) {
       String[] arr = s.split(" ");
        
        int answer = 0;
        
        int i = 0;
        while(i < arr.length) {
            if(i+1 < arr.length && arr[i+1].equals("Z")) {
                i+=2;
            } else {
                answer += Integer.parseInt(arr[i]);
                i++;
            }
        }    
        
        return answer; 
    }
    
    /*
    * Stack 활용: Z 직전에 저장한 (가장 최근에 저장한) 숫자를 제거
    */
    private int useStack(String s) {
        Stack<Integer> stack = new Stack<>();
        
        for(String piece : s.split(" ")) {
            if(piece.equals("Z")) {
                stack.pop();
            } else {
                stack.push(Integer.parseInt(piece));
            }
        }
        
        int answer = 0;
        for(int num : stack) {
            answer += num;
        }
        
        return answer;
    }
    
    public int solution(String s) {
        return useStack(s);
    }
}