import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Integer> stack = new Stack<>();
        
        char[] chars = number.toCharArray();
        
        // 스택에서 "이전 숫자 < 지금 숫자"일 경우, 이전 숫자를 제거 :: 아니라면 지금 숫자를 추가
        // 4177 같은 경우, 1<7, 4<7 이므로 while - 가장 큰 숫자가 나올 때까지 반복
        // 제거는 k번까지만 가능
        for(char c : chars) {
            while (!stack.empty() && stack.peek() < Character.getNumericValue(c) && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(Character.getNumericValue(c));
        }
        
        // 제거할 숫자가 남을 경우(마지막 숫자가 이전 숫자와 똑같은 경우): 제거
        if(k > 0) stack.pop(); 
        
        // 스택의 '[',']',',' 제거하고 숫자만 리턴
        return stack.toString().replaceAll("[^0-9]","");
    }
}